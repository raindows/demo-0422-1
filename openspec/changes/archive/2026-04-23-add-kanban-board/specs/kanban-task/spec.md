## ADDED Requirements

### Requirement: Create task
系统 SHALL 允许创建任务卡片，需提供 `title` 字段。`title` 不可为空。`description` 为可选字段。`status` 为可选字段，若提供则 MUST 对应一个已存在的 Column ID。

#### Scenario: Successfully create a task with status
- **WHEN** 用户提交 POST 请求到 `/tasks`，body 包含 `{"title": "实现登录", "description": "完成用户登录功能", "status": "<columnId>"}`
- **THEN** 系统创建任务并返回 HTTP 201，响应体包含创建的任务对象（含自动生成的 `id`）

#### Scenario: Create task without status
- **WHEN** 用户提交 POST 请求到 `/tasks`，body 包含 `{"title": "实现登录"}`
- **THEN** 系统创建任务，`status` 为 `null`，返回 HTTP 201

#### Scenario: Create task with empty title
- **WHEN** 用户提交 POST 请求到 `/tasks`，body 包含 `{"title": ""}`
- **THEN** 系统返回 HTTP 400，错误信息提示 title 不可为空

#### Scenario: Create task with non-existent column id
- **WHEN** 用户提交 POST 请求到 `/tasks`，body 包含 `{"title": "实现登录", "status": "<invalidId>"}`
- **THEN** 系统返回 HTTP 400，错误信息提示 status 对应的列不存在

### Requirement: List tasks
系统 SHALL 返回所有任务卡片。支持通过 `status` 查询参数筛选特定列下的任务。

#### Scenario: Get all tasks
- **WHEN** 用户发送 GET 请求到 `/tasks`
- **THEN** 系统返回 HTTP 200，响应体为任务数组

#### Scenario: Filter tasks by status
- **WHEN** 用户发送 GET 请求到 `/tasks?status=<columnId>`
- **THEN** 系统返回 HTTP 200，响应体为该列下的任务数组

#### Scenario: Filter by non-existent column id
- **WHEN** 用户发送 GET 请求到 `/tasks?status=<invalidId>`
- **THEN** 系统返回 HTTP 200，响应体为空数组 `[]`

### Requirement: Update task
系统 SHALL 允许更新任务的 `title`、`description` 和 `status` 字段。更新 `status` 时，新值 MUST 对应一个已存在的 Column ID。

#### Scenario: Successfully update task status (move to another column)
- **WHEN** 用户发送 PUT 请求到 `/tasks/:id`，body 包含 `{"status": "<newColumnId>"}`
- **THEN** 系统更新任务的 status 并返回 HTTP 200，响应体包含更新后的任务对象

#### Scenario: Update task title
- **WHEN** 用户发送 PUT 请求到 `/tasks/:id`，body 包含 `{"title": "新标题"}`
- **THEN** 系统更新任务的 title 并返回 HTTP 200

#### Scenario: Update non-existent task
- **WHEN** 用户发送 PUT 请求到 `/tasks/:id`，但该 id 不存在
- **THEN** 系统返回 HTTP 404

#### Scenario: Update task with non-existent column id
- **WHEN** 用户发送 PUT 请求到 `/tasks/:id`，body 包含 `{"status": "<invalidId>"}`
- **THEN** 系统返回 HTTP 400，错误信息提示 status 对应的列不存在

### Requirement: Delete task
系统 SHALL 允许删除任务卡片。

#### Scenario: Successfully delete a task
- **WHEN** 用户发送 DELETE 请求到 `/tasks/:id`
- **THEN** 系统删除该任务并返回 HTTP 204

#### Scenario: Delete non-existent task
- **WHEN** 用户发送 DELETE 请求到 `/tasks/:id`，但该 id 不存在
- **THEN** 系统返回 HTTP 404
