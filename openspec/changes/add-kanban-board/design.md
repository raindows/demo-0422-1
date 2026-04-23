## Context

当前项目是一个新建的看板管理系统，需要从零开始构建核心数据模型和 API。系统基于 Node.js/TypeScript 技术栈，使用 RESTful API 风格。

## Goals / Non-Goals

**Goals:**

- 定义 Column 和 Task 的数据模型及存储结构
- 实现完整的 CRUD API
- Column 支持按 position 排序
- Task 的 status 字段与 Column 关联，实现任务流转

**Non-Goals:**

- 不实现用户认证和权限管理
- 不实现实时协作（WebSocket）
- 不实现拖拽排序的前端交互
- 不实现任务评论、附件等高级功能

## Decisions

**1. 数据模型：Column 与 Task 一对多关系**

Column 通过 `id` 关联 Task 的 `status` 字段（status 存储对应 Column 的 ID）。这避免了在 Task 上维护冗余的状态字符串，同时使列可以动态创建和管理。

备选方案：Task.status 存储字符串枚举 → 放弃，因为列名称可自定义，字符串枚举缺乏灵活性。

**2. 存储：SQLite 作为初始存储**

使用 SQLite 作为轻量级存储，无需额外依赖数据库服务，适合项目初期快速迭代。

备选方案：PostgreSQL → 放弃，当前阶段引入外部数据库服务增加部署复杂度。

**3. API 风格：RESTful**

采用标准 RESTful API 设计，资源路径清晰（`/columns`、`/tasks`），HTTP 方法语义明确（GET/POST/PUT/DELETE）。

## Risks / Trade-offs

- **SQLite 并发写入限制** → 单用户/小团队场景可接受，后续可迁移至 PostgreSQL
- **Column 删除时关联 Task 处理** → 删除 Column 时，将其下 Task 的 status 置为 null 或默认列，需要明确策略
