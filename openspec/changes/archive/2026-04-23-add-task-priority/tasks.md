## 1. Database Migration

- [x] 1.1 在 schema.sql 中为 tasks 表新增 `priority TEXT NOT NULL DEFAULT 'medium'` 列
- [x] 1.2 验证已有数据自动填充默认值

## 2. Backend Model & Mapper

- [x] 2.1 Task.java 新增 `priority` 字段（String 类型）
- [x] 2.2 TaskMapper.xml 中所有 SQL 语句包含 priority 字段（insert、selectAll、selectById、update）
- [x] 2.3 TaskController.java 新增 priority 白名单校验（high/medium/low），非法值返回 400

## 3. Frontend Task Form

- [x] 3.1 TaskForm.vue 新增 priority 下拉选择器（High/Medium/Low，默认选中 Medium）

## 4. Frontend Task Card

- [x] 4.1 KanbanTask.vue 根据 task.priority 渲染左侧 3px 彩色边框（High=#F43F5E, Medium=#5E3BEE, Low=#3B82F6）
- [x] 4.2 style.css 新增 `.kanban-task--priority-high`、`.kanban-task--priority-medium`、`.kanban-task--priority-low` 样式
