package com.example.hello.controller;

import com.example.hello.dto.ApiResponse;
import com.example.hello.dto.StudentRequest;
import com.example.hello.entity.Student;
import com.example.hello.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生控制器类
 * 提供学生相关的RESTful API接口
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // 允许跨域访问
public class StudentController {
    
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    
    @Autowired
    private StudentService studentService;
    
    /**
     * 查询所有学生信息
     * GET /api/get-student
     * @return 包含所有学生信息的响应
     */
    @GetMapping("/get-student")
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        logger.info("收到查询所有学生信息的请求");
        try {
            List<Student> students = studentService.getAllStudents();
            ApiResponse<List<Student>> response = ApiResponse.success("查询成功", students);
            logger.info("成功返回 {} 个学生信息", students.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("查询所有学生信息时发生错误: {}", e.getMessage(), e);
            ApiResponse<List<Student>> response = ApiResponse.error("查询学生信息失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 添加新的学生
     * POST /api/add-student
     * @param request 学生请求对象
     * @return 新添加的学生信息
     */
    @PostMapping("/add-student")
    public ResponseEntity<ApiResponse<Student>> addStudent(@Valid @RequestBody StudentRequest request) {
        logger.info("收到添加新学生的请求，学生信息: {}", request.getValue());
        try {
            Student newStudent = studentService.addStudent(request);
            ApiResponse<Student> response = ApiResponse.success("添加学生成功", newStudent);
            logger.info("成功添加学生，ID: {}", newStudent.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("添加学生时发生错误: {}", e.getMessage(), e);
            ApiResponse<Student> response = ApiResponse.error("添加学生失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 更新学生信息状态
     * POST /api/update-student/{id}
     * @param id 学生ID
     * @return 更新后的学生信息
     */
    @PostMapping("/update-student/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudentStatus(@PathVariable Long id) {
        logger.info("收到更新学生状态的请求，学生ID: {}", id);
        try {
            Student updatedStudent = studentService.updateStudentStatus(id);
            ApiResponse<Student> response = ApiResponse.success("更新学生状态成功", updatedStudent);
            logger.info("成功更新学生状态，学生ID: {}, 新状态: {}", id, updatedStudent.getIsCompleted());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            logger.warn("更新学生状态失败: {}", e.getMessage());
            ApiResponse<Student> response = ApiResponse.error(404, "学生不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            logger.error("更新学生状态时发生错误: {}", e.getMessage(), e);
            ApiResponse<Student> response = ApiResponse.error("更新学生状态失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 删除学生信息
     * POST /api/del-student/{id}
     * @param id 学生ID
     * @return 删除操作结果
     */
    @PostMapping("/del-student/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteStudent(@PathVariable Long id) {
        logger.info("收到删除学生的请求，学生ID: {}", id);
        try {
            boolean result = studentService.deleteStudent(id);
            if (result) {
                ApiResponse<Boolean> response = ApiResponse.success("删除学生成功", true);
                logger.info("成功删除学生，学生ID: {}", id);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Boolean> response = ApiResponse.error(404, "学生不存在");
                logger.warn("删除学生失败，学生不存在，学生ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            logger.error("删除学生时发生错误: {}", e.getMessage(), e);
            ApiResponse<Boolean> response = ApiResponse.error("删除学生失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 根据ID查询学生信息
     * GET /api/get-student/{id}
     * @param id 学生ID
     * @return 学生信息
     */
    @GetMapping("/get-student/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Long id) {
        logger.info("收到查询学生信息的请求，学生ID: {}", id);
        try {
            Student student = studentService.getStudentById(id);
            ApiResponse<Student> response = ApiResponse.success("查询成功", student);
            logger.info("成功查询到学生信息，学生ID: {}", id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            logger.warn("查询学生信息失败: {}", e.getMessage());
            ApiResponse<Student> response = ApiResponse.error(404, "学生不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            logger.error("查询学生信息时发生错误: {}", e.getMessage(), e);
            ApiResponse<Student> response = ApiResponse.error("查询学生信息失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
} 