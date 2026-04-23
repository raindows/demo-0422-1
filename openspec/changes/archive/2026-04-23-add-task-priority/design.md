## Context

看板系统已有 Column 和 Task 两个实体，Task 当前字段为 `id`、`title`、`description`、`status`。后端为 Spring Boot + MyBatis + SQLite，前端为 Vue 3。需为 Task 增加优先级字段以支持任务分层管理。

## Goals / Non-Goals

**Goals:**
- Task 实体新增 `priority` 字段（high / medium / low，默认 medium）
- 数据库平滑迁移，已有任务自动填充默认值
- API 全链路支持 priority 的 CRUD
- 前端任务卡片左侧彩色边框标识优先级

**Non-Goals:**
- 不实现按优先级排序/筛选的 API（可后续扩展）
- 不修改 Column 实体
- 不引入优先级权重数值（仅枚举三档）

## Decisions

**1. 存储方式：TEXT 枚举而非整数映射**
使用 `TEXT` 存储 `'high'`/`'medium'`/`'low'` 而非 INTEGER(1/2/3)。优势：API 响应可读性好，无需额外映射逻辑，SQLite 中 TEXT 开销可忽略。
备选方案：INTEGER 映射 → 放弃，增加前后端映射复杂度。

**2. 默认值：数据库层 DEFAULT 'medium'**
在 ALTER TABLE 中设置 `DEFAULT 'medium'`，已有数据自动填充。无需编写数据迁移脚本。
备选方案：应用层回填 → 放弃，增加启动时复杂度。

**3. 校验策略：白名单校验**
后端在 Create/Update 时校验 priority 值必须在 `['high', 'medium', 'low']` 范围内，非法值返回 400。

**4. 前端视觉：左侧 3px 彩色边框**
- High: `#F43F5E`（红）
- Medium: `#5E3BEE`（紫，与主色一致）
- Low: `#3B82F6`（蓝）
使用 `border-left: 3px solid` 实现，不增加额外 DOM 元素。

## Risks / Trade-offs

- **SQLite ALTER TABLE** → SQLite 支持 ALTER TABLE ADD COLUMN，兼容性无风险
- **枚举扩展性** → 若未来需要更多优先级（如 critical），需修改白名单和前端样式，但三档覆盖绝大多数场景
