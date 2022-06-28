package com.hemanth.todoAssignment.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;
    Todo todo;
    @BeforeEach
    void setUp() {
        todo = new Todo(1L, "Task #1", "This is the first todo task", false);
    }

    @Test
    void shouldBeAbleToAddTodoTask() {
        todoRepository.save(todo);

        assertEquals(todoRepository.findById(todo.getId()).get(), todo);
    }

    @Test
    void shouldBeAbleToUpdateAnExistingTodoTask() {
        todoRepository.save(todo);
        String updatedDescription = "This is the [UPDATED] first todo task";

        todo.setDescription(updatedDescription);
        todoRepository.save(todo);

        assertEquals(todoRepository.findById(todo.getId()).get().getDescription(), updatedDescription);
    }
}
