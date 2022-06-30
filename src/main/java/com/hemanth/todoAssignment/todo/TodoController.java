package com.hemanth.todoAssignment.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAll());
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        todoService.add(todo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        todoService.update(todo);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long id) {
        todoService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
