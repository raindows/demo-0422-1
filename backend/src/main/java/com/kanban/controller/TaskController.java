package com.kanban.controller;

import com.kanban.exception.BadRequestException;
import com.kanban.exception.NotFoundException;
import com.kanban.mapper.ColumnMapper;
import com.kanban.mapper.TaskMapper;
import com.kanban.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskMapper taskMapper;
    private final ColumnMapper columnMapper;
    private static final Set<String> VALID_PRIORITIES = Set.of("high", "medium", "low");

    public TaskController(TaskMapper taskMapper, ColumnMapper columnMapper) {
        this.taskMapper = taskMapper;
        this.columnMapper = columnMapper;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        if (task.getTitle() == null || task.getTitle().isBlank()) {
            throw new BadRequestException("title must not be empty");
        }
        if (task.getStatus() != null && columnMapper.selectById(task.getStatus()) == null) {
            throw new BadRequestException("status column does not exist");
        }
        if (task.getPriority() != null && !VALID_PRIORITIES.contains(task.getPriority())) {
            throw new BadRequestException("priority must be one of: high, medium, low");
        }
        if (task.getPriority() == null) {
            task.setPriority("medium");
        }
        taskMapper.insert(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping
    public List<Task> list(@RequestParam(required = false) Long status) {
        if (status != null) {
            return taskMapper.selectByStatus(status);
        }
        return taskMapper.selectAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task) {
        Task existing = taskMapper.selectById(id);
        if (existing == null) {
            throw new NotFoundException("Task not found");
        }
        if (task.getStatus() != null && columnMapper.selectById(task.getStatus()) == null) {
            throw new BadRequestException("status column does not exist");
        }
        if (task.getPriority() != null && !VALID_PRIORITIES.contains(task.getPriority())) {
            throw new BadRequestException("priority must be one of: high, medium, low");
        }
        task.setId(id);
        taskMapper.update(task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Task existing = taskMapper.selectById(id);
        if (existing == null) {
            throw new NotFoundException("Task not found");
        }
        taskMapper.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
