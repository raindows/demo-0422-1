package com.kanban.model;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Long status;
    private String priority;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getStatus() { return status; }
    public void setStatus(Long status) { this.status = status; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}
