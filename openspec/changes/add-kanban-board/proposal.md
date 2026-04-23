## Why

项目需要一个看板管理系统来可视化和管理工作流程。通过看板列（Column）和任务卡片（Task）两个核心实体，实现任务在不同阶段间的流转管理。

## What Changes

- 新增 **Column（看板列）** 实体，包含 `name`（列名称）和 `position`（排序位置）属性
- 新增 **Task（任务卡片）** 实体，包含 `title`（标题）、`description`（描述）和 `status`（状态）属性
- 提供 Column 的 CRUD 接口，支持按 position 排序
- 提供 Task 的 CRUD 接口，支持按 status 筛选
- Task 的 status 与 Column 建立关联

## Capabilities

### New Capabilities

- `kanban-column`: 看板列管理，包括列的创建、查询、更新、删除及排序
- `kanban-task`: 任务卡片管理，包括任务的创建、查询、更新、删除及状态流转

### Modified Capabilities

（无现有能力需要修改）

## Impact

- 新增数据模型和数据库表（columns、tasks）
- 新增 RESTful API 端点
- 依赖数据库存储层
