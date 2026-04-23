package com.kanban.controller;

import com.kanban.exception.BadRequestException;
import com.kanban.exception.NotFoundException;
import com.kanban.mapper.ColumnMapper;
import com.kanban.mapper.TaskMapper;
import com.kanban.model.Column;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/columns")
public class ColumnController {

    private final ColumnMapper columnMapper;
    private final TaskMapper taskMapper;

    public ColumnController(ColumnMapper columnMapper, TaskMapper taskMapper) {
        this.columnMapper = columnMapper;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<Column> create(@RequestBody Column column) {
        if (column.getName() == null || column.getName().isBlank()) {
            throw new BadRequestException("name must not be empty");
        }
        if (column.getPosition() == null || column.getPosition() < 0) {
            throw new BadRequestException("position must be a non-negative integer");
        }
        columnMapper.insert(column);
        return ResponseEntity.status(HttpStatus.CREATED).body(column);
    }

    @GetMapping
    public List<Column> list() {
        return columnMapper.selectAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Column> update(@PathVariable Long id, @RequestBody Column column) {
        Column existing = columnMapper.selectById(id);
        if (existing == null) {
            throw new NotFoundException("Column not found");
        }
        column.setId(id);
        columnMapper.update(column);
        return ResponseEntity.ok(column);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Column existing = columnMapper.selectById(id);
        if (existing == null) {
            throw new NotFoundException("Column not found");
        }
        taskMapper.nullifyStatusByColumnId(id);
        columnMapper.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
