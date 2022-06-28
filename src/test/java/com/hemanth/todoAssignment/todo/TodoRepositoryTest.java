package com.hemanth.todoAssignment.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void shouldBeAbleToAddTodoTask() {
        Todo todo = new Todo(1L, "Task #1", "This is the first todo task", false);
        todoRepository.save(todo);

        assertEquals(todoRepository.findById(todo.getId()).get(), todo);
    }

    @Test
    void shouldBeAbleToUpdateAnExistingTodoTask() {
        Todo todo = new Todo(2L, "Task #2", "This is the second todo task", false);
        todoRepository.save(todo);
        String updatedDescription = "This is the [UPDATED] second todo task";

        todo.setDescription(updatedDescription);
        todoRepository.save(todo);

        assertEquals(todoRepository.findById(todo.getId()).get(), todo);
    }

    @Test
    void shouldBeAbleToDeleteTheExistingTodoTask() {
        Todo todo = new Todo(3L, "Task #3", "This is the three todo task", false);
        todoRepository.save(todo);

        todoRepository.deleteById(todo.getId());

        assertFalse(todoRepository.existsById(todo.getId()));
    }
}
