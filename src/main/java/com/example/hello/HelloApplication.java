package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 教师管理系统主应用类
 * 
 * 这是一个基于Spring Boot的教师管理后端API项目，提供以下功能：
 * 1. 查询所有学生信息 (GET /api/get-student)
 * 2. 添加新的学生 (POST /api/add-student)
 * 3. 更新学生信息状态 (POST /api/update-student/{id})
 * 4. 删除学生信息 (POST /api/del-student/{id})
 * 
 * 技术栈：
 * - Spring Boot 3.3.2
 * - Spring Data JPA
 * - MySQL数据库
 * - Spring Validation
 * - SLF4J日志
 * 
 * @author 开发团队
 * @version 1.0.0
 */
@SpringBootApplication
public class HelloApplication {

    /**
     * 应用程序入口方法
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}
