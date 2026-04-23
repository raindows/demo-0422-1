## Why

当前 Task 实体仅有 title、description、status 三个字段，缺乏优先级标识。用户无法区分任务的紧急程度，导致在任务较多时难以聚焦关键工作。增加 Priority 字段可为看板提供直观的视觉分层。

## What Changes

- Task 实体新增 `priority` 字段（枚举值：`high`、`medium`、`low`），默认值为 `medium`
- Task CRUD API 支持 priority 字段的创建、读取、更新
- 数据库 tasks 表新增 `priority` 列，已有数据默认填充 `medium`
- 前端任务卡片根据优先级渲染左侧彩色边框标识（High=红，Medium=紫，Low=蓝）

## Capabilities

### New Capabilities

（无新增能力）

### Modified Capabilities

- `kanban-task`: 新增 priority 字段的 CRUD 行为约束及校验规则

## Impact

- 数据库：tasks 表新增 priority 列（TEXT，默认 `'medium'`），需 ALTER TABLE 迁移
- 后端：Task model/mapper/controller 需适配新字段
- 前端：TaskForm 新增优先级选择器，KanbanTask 卡片新增优先级边框样式
- 向后兼容：已有任务自动填充默认值 `medium`，无需手动迁移
