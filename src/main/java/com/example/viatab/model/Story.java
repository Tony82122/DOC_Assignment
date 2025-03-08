package com.example.viatab.model;

import java.time.LocalDateTime;

public class Story {
    private Long id;
    private String title;
    private String content;
    private String department;
    private LocalDateTime createdAt;

    public Story() {
        this.createdAt = LocalDateTime.now();
    }

    public Story(Long id, String title, String content, String department) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.department = department;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}