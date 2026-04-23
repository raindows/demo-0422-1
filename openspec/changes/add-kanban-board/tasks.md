## 1. 项目初始化

- [ ] 1.1 初始化 Node.js/TypeScript 项目，配置 tsconfig.json
- [ ] 1.2 安装依赖：express、better-sqlite3 及其类型定义
- [ ] 1.3 创建项目目录结构（src/routes、src/models、src/db）

## 2. 数据库与数据模型

- [ ] 2.1 创建 SQLite 数据库连接模块（src/db/connection.ts）
- [ ] 2.2 创建 columns 表：id INTEGER PRIMARY KEY, name TEXT NOT NULL, position INTEGER NOT NULL
- [ ] 2.3 创建 tasks 表：id INTEGER PRIMARY KEY, title TEXT NOT NULL, description TEXT, status INTEGER REFERENCES columns(id)
- [ ] 2.4 定义 Column 和 Task 的 TypeScript 类型（src/models/column.ts、src/models/task.ts）

## 3. Column CRUD API

- [ ] 3.1 实现 POST /columns — 创建看板列，校验 name 非空、position 为非负整数
- [ ] 3.2 实现 GET /columns — 查询所有列，按 position 升序排列
- [ ] 3.3 实现 PUT /columns/:id — 更新列的 name 或 position，不存在时返回 404
- [ ] 3.4 实现 DELETE /columns/:id — 删除列，并将关联 Task 的 status 置为 null，不存在时返回 404

## 4. Task CRUD API

- [ ] 4.1 实现 POST /tasks — 创建任务，校验 title 非空，status 引用校验
- [ ] 4.2 实现 GET /tasks — 查询所有任务，支持 ?status=<columnId> 筛选
- [ ] 4.3 实现 PUT /tasks/:id — 更新任务，status 引用校验，不存在时返回 404
- [ ] 4.4 实现 DELETE /tasks/:id — 删除任务，不存在时返回 404

## 5. 应用入口与集成

- [ ] 5.1 创建 Express 应用入口（src/index.ts），挂载 Column 和 Task 路由
- [ ] 5.2 添加 JSON body 解析中间件和错误处理
- [ ] 5.3 配置 package.json scripts（build、start、dev）
