package com.hemanth.todoAssignment.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public void add(Todo todo){
        todoRepository.save(todo);
    }

    public Optional<Todo> getById(Long id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public void update(Todo todo) {
        add(todo);
    }

    public void removeById(Long id){
        todoRepository.deleteById(id);
    }
}
