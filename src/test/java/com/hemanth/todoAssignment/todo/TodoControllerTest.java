package com.hemanth.todoAssignment.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void shouldBeAbleToGetAllTodosWhenGetRequestIsSent() throws Exception {
        List<Todo> todos = Arrays.asList(
                new Todo(1L, "Task #1", "First Task", false),
                new Todo(2L, "Task #2", "Second Task", false),
                new Todo(3L, "Task #3", "Third Task", false),
                new Todo(4L, "Task #4", "Fourth Task", false)
        );
        when(todoService.getAll()).thenReturn(todos);
        int todoGetAllMethodIsCalled = 1;

        mockMvc.perform(MockMvcRequestBuilders.get("/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(todoService, times(todoGetAllMethodIsCalled)).getAll();
    }

    @Test
    void shouldBeAbleToAddATodoWhenPostRequestIsSent() throws Exception {
        Todo todo = new Todo(1L, "Task #1", "First Task", false);
        ObjectMapper objectMapper = new ObjectMapper();
        int todoAddMethodIsCalled = 1;

        mockMvc.perform(MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isCreated());

        verify(todoService, times(todoAddMethodIsCalled)).add(todo);
    }

    @Test
    void shouldBeAbleToUpdateExistingTodoWhenPutRequestIsSent() throws Exception {
        Todo todo = new Todo(1L, "Task #1", "First Task", false);
        Todo updatedTodo = new Todo(1L, "Task #1", "This is my [UPDATED] Task", true);
        ObjectMapper objectMapper = new ObjectMapper();
        int todoUpdateMethodIsCalled = 1;

        mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", todo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTodo)))
                .andExpect(status().isAccepted());

        verify(todoService, times(todoUpdateMethodIsCalled)).update(updatedTodo);
    }

    @Test
    void shouldBeAbleToDeleteTheTodoWhenDeleteRequestIsSent() throws Exception {
        Todo todo = new Todo(1L, "Task #1", "First Task", false);
        int todoRemoveByIdMethodIsCalled = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}", todo.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(todoService, times(todoRemoveByIdMethodIsCalled)).removeById(todo.getId());
    }
}