package com.kanban.mapper;

import com.kanban.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    void insert(Task task);
    List<Task> selectAll();
    List<Task> selectByStatus(@Param("status") Long status);
    Task selectById(@Param("id") Long id);
    void update(Task task);
    void deleteById(@Param("id") Long id);
    void nullifyStatusByColumnId(@Param("columnId") Long columnId);
}
