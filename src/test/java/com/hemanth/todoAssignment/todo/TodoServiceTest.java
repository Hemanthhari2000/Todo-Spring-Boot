package com.hemanth.todoAssignment.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;
    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo(1L, "Task #1", "First Task", false);
    }

    @Test
    void shouldBeAbleToAddTodoTaskUsingTodoService() {
        todoService.add(todo);

        verify(todoRepository).save(todo);
    }

    @Test
    void shouldBeAbleToGetTodoByIdUsingTodoService() {
        todoService.getById(todo.getId());

        verify(todoRepository).findById(todo.getId());
    }

    @Test
    void shouldBeAbleToGetAllTodoUsingTodoService() {
        todoService.getAll();

        verify(todoRepository).findAll();
    }

    @Test
    void shouldBeAbleToUpdateTheExistingTodoUsingTodoService() {
        todoService.update(todo);

        verify(todoRepository).save(todo);
    }

    @Test
    void shouldBeAbleToDeleteTheExistingTodoUsingTodoService() {
        todoService.removeById(todo.getId());

        verify(todoRepository).deleteById(todo.getId());
    }

}