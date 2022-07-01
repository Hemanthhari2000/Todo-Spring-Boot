package com.hemanth.todoAssignment.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class TodoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private TodoService todoService;

    @Test
    void shouldReturnEmptyTodoListWhenGetTodosEndpointIsCalledForTheFirstTime() throws Exception {
        String expectedResponse = "[]";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldBeAbleToAddTodoWhenAddTodoEndpointIsCalled() throws Exception {
        Todo todo = new Todo(1L, "Task #1", "First Task", false);
        String todoAsString = new ObjectMapper().writeValueAsString(todo);
        int expectedStatusCode = 201;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoAsString))
                .andExpect(status().isCreated())
                .andReturn();

        assertEquals(expectedStatusCode, mvcResult.getResponse().getStatus());
    }

    @Test
    void shouldReturnAllTodosWhenGetTodoEndpointIsCalledAfterAddingSomeTodos() throws Exception {
        List<Todo> todos = Arrays.asList(
                new Todo(1L, "Task #1", "First Task", false),
                new Todo(2L, "Task #2", "Second Task", false),
                new Todo(3L, "Task #3", "Third Task", false),
                new Todo(4L, "Task #4", "Fourth Task", false)
        );
        String expectedResponse = new ObjectMapper().writeValueAsString(todos);
        when(todoService.getAll()).thenReturn(todos);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldBeAbleToUpdateExistingTodoWhenUpdateTodoEndpointIsCalled() throws Exception {
        Todo todo = new Todo(1L, "Task #1", "First Task", false);
        todo.setDescription("Updated First Task");
        when(todoService.getById(todo.getId())).thenReturn(Optional.of(todo));
        int expectedResponseCode = 202;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", todo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(todo)))
                .andReturn();

        int actualResponseCode = mvcResult.getResponse().getStatus();
        assertEquals(expectedResponseCode, actualResponseCode);
    }

    @Test
    void shouldBeAbleToDeleteTodoWhenDeleteTodoEndpointIsCalled() throws Exception {
        Todo todo = new Todo(1L, "Task #1", "First Task", false);
        int expectedResponseCode = 200;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}", todo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(todo)))
                .andReturn();

        int actualResponseCode = mvcResult.getResponse().getStatus();
        assertEquals(expectedResponseCode, actualResponseCode);

    }
}

