# 教师管理系统后端API

这是一个基于Spring Boot的教师管理后端API项目，提供学生信息的增删改查功能。

## 项目概述

本项目是一个RESTful API服务，使用Spring Boot框架构建，MySQL作为数据库，提供完整的学生信息管理功能。

## 技术栈

- **Spring Boot 3.3.2** - 主框架
- **Spring Data JPA** - 数据访问层
- **MySQL** - 数据库
- **Spring Validation** - 数据验证
- **SLF4J** - 日志记录
- **Maven** - 项目管理

## 数据库配置

数据库连接信息：
- 主机：test-db-mysql.ns-8tuig8ui.svc
- 端口：3306
- 数据库：teacher_db
- 用户名：root
- 密码：trw26hv8

## API接口文档

### 1. 查询所有学生信息

- **接口**: `GET /api/get-student`
- **功能**: 从数据库查询并返回所有学生信息
- **参数**: 无
- **返回**: 包含所有学生信息的数组

**响应示例**:
```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "id": 1,
      "value": "张三 - 计算机科学专业",
      "isCompleted": false,
      "createdAt": "2024-01-01T10:00:00",
      "updatedAt": "2024-01-01T10:00:00"
    }
  ],
  "timestamp": "2024-01-01T10:00:00"
}
```

### 2. 添加新的学生

- **接口**: `POST /api/add-student`
- **功能**: 向数据库添加新的学生信息
- **参数**: 
  ```json
  {
    "value": "学生信息内容",
    "isCompleted": false
  }
  ```
- **返回**: 新添加的学生信息对象

**请求示例**:
```json
{
  "value": "李四 - 软件工程专业",
  "isCompleted": false
}
```

### 3. 更新学生信息状态

- **接口**: `POST /api/update-student/{id}`
- **功能**: 根据id更新指定学生的完成状态（将isCompleted值取反）
- **参数**: id (路径参数)
- **返回**: 更新后的学生信息对象

### 4. 删除学生信息

- **接口**: `POST /api/del-student/{id}`
- **功能**: 根据id删除指定的学生信息
- **参数**: id (路径参数)
- **返回**: 删除操作的结果状态

## 项目结构

```
src/main/java/com/example/hello/
├── HelloApplication.java          # 主应用类
├── controller/
│   └── StudentController.java     # 学生控制器
├── service/
│   └── StudentService.java        # 学生服务层
├── repository/
│   └── StudentRepository.java     # 学生数据访问层
├── entity/
│   └── Student.java              # 学生实体类
├── dto/
│   ├── StudentRequest.java       # 学生请求DTO
│   └── ApiResponse.java          # API响应DTO
└── exception/
    └── GlobalExceptionHandler.java # 全局异常处理器
```

## 运行项目

### 前置条件

1. 确保MySQL数据库已启动并可访问
2. 确保数据库`teacher_db`已创建
3. 确保Java 17已安装

### 启动步骤

1. 克隆项目到本地
2. 进入项目目录
3. 运行以下命令启动项目：

```bash
./mvnw spring-boot:run
```

或者

```bash
mvn spring-boot:run
```

### 访问API

项目启动后，可以通过以下地址访问API：

- 基础URL: `http://localhost:8080`
- API文档: `http://localhost:8080/api/get-student`

## 功能特性

- ✅ RESTful API设计
- ✅ 完整的CRUD操作
- ✅ 数据验证和错误处理
- ✅ 详细的日志记录
- ✅ 跨域支持
- ✅ 统一响应格式
- ✅ 全局异常处理

## 日志配置

项目配置了详细的日志记录，包括：
- SQL查询日志
- 请求处理日志
- 错误日志
- 性能监控日志

## 开发说明

### 添加新功能

1. 在`entity`包中创建实体类
2. 在`repository`包中创建数据访问接口
3. 在`service`包中实现业务逻辑
4. 在`controller`包中创建API接口
5. 在`dto`包中创建数据传输对象

### 测试API

可以使用Postman或curl等工具测试API：

```bash
# 查询所有学生
curl -X GET http://localhost:8080/api/get-student

# 添加学生
curl -X POST http://localhost:8080/api/add-student \
  -H "Content-Type: application/json" \
  -d '{"value": "王五 - 数据科学专业", "isCompleted": false}'

# 更新学生状态
curl -X POST http://localhost:8080/api/update-student/1

# 删除学生
curl -X POST http://localhost:8080/api/del-student/1
```

## 许可证

本项目采用MIT许可证。 