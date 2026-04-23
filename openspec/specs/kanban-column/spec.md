## ADDED Requirements

### Requirement: Create column
系统 SHALL 允许创建看板列，需提供 `name` 和 `position` 字段。`name` 不可为空，`position` 必须为非负整数。

#### Scenario: Successfully create a column
- **WHEN** 用户提交 POST 请求到 `/columns`，body 包含 `{"name": "待办", "position": 0}`
- **THEN** 系统创建列并返回 HTTP 201，响应体包含创建的列对象（含自动生成的 `id`）

#### Scenario: Create column with empty name
- **WHEN** 用户提交 POST 请求到 `/columns`，body 包含 `{"name": "", "position": 0}`
- **THEN** 系统返回 HTTP 400，错误信息提示 name 不可为空

### Requirement: List columns
系统 SHALL 返回所有看板列，按 `position` 升序排列。

#### Scenario: Get all columns sorted by position
- **WHEN** 用户发送 GET 请求到 `/columns`
- **THEN** 系统返回 HTTP 200，响应体为列数组，按 `position` 升序排列

#### Scenario: No columns exist
- **WHEN** 用户发送 GET 请求到 `/columns`，且系统中没有任何列
- **THEN** 系统返回 HTTP 200，响应体为空数组 `[]`

### Requirement: Update column
系统 SHALL 允许更新看板列的 `name` 和 `position` 字段。

#### Scenario: Successfully update column name
- **WHEN** 用户发送 PUT 请求到 `/columns/:id`，body 包含 `{"name": "进行中"}`
- **THEN** 系统更新列的 name 并返回 HTTP 200，响应体包含更新后的列对象

#### Scenario: Update non-existent column
- **WHEN** 用户发送 PUT 请求到 `/columns/:id`，但该 id 不存在
- **THEN** 系统返回 HTTP 404

### Requirement: Delete column
系统 SHALL 允许删除看板列。删除列时，该列下所有 Task 的 `status` SHALL 被置为 `null`。

#### Scenario: Successfully delete a column with tasks
- **WHEN** 用户发送 DELETE 请求到 `/columns/:id`，该列下有 2 个 Task
- **THEN** 系统删除该列，返回 HTTP 204，且这 2 个 Task 的 `status` 被置为 `null`

#### Scenario: Delete non-existent column
- **WHEN** 用户发送 DELETE 请求到 `/columns/:id`，但该 id 不存在
- **THEN** 系统返回 HTTP 404
