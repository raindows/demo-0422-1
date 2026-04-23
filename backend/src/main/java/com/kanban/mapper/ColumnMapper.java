package com.kanban.mapper;

import com.kanban.model.Column;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ColumnMapper {
    void insert(Column column);
    List<Column> selectAll();
    Column selectById(@Param("id") Long id);
    void update(Column column);
    void deleteById(@Param("id") Long id);
}
