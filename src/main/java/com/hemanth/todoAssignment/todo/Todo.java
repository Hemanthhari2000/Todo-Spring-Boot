package com.hemanth.todoAssignment.todo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;
    private String description;
    private Boolean isCompleted;

    public Todo() {}

    public Todo(Long id, String task, String description, Boolean isCompleted) {
        this.id = id;
        this.task = task;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id.equals(todo.id) && task.equals(todo.task) && Objects.equals(description, todo.description) && isCompleted.equals(todo.isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, task, description, isCompleted);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
