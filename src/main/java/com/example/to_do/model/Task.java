package com.example.to_do.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
//@Table(name = "tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 150)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean completed;

    public Task() {
    }

    public Task(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

