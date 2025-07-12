package com.example.hello.service;

import com.example.hello.dto.StudentRequest;
import com.example.hello.entity.Student;
import com.example.hello.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 学生服务类
 * 处理学生相关的业务逻辑
 */
@Service
@Transactional
public class StudentService {
    
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    
    @Autowired
    private StudentRepository studentRepository;
    
    /**
     * 获取所有学生信息
     * @return 学生列表
     */
    public List<Student> getAllStudents() {
        logger.info("开始查询所有学生信息");
        try {
            List<Student> students = studentRepository.findAllByOrderByCreatedAtDesc();
            logger.info("成功查询到 {} 个学生信息", students.size());
            return students;
        } catch (Exception e) {
            logger.error("查询所有学生信息时发生错误: {}", e.getMessage(), e);
            throw new RuntimeException("查询学生信息失败", e);
        }
    }
    
    /**
     * 添加新学生
     * @param request 学生请求对象
     * @return 新创建的学生对象
     */
    public Student addStudent(StudentRequest request) {
        logger.info("开始添加新学生，学生信息: {}", request.getValue());
        try {
            Student student = new Student();
            student.setValue(request.getValue());
            student.setIsCompleted(request.getIsCompleted() != null ? request.getIsCompleted() : false);
            
            Student savedStudent = studentRepository.save(student);
            logger.info("成功添加学生，ID: {}", savedStudent.getId());
            return savedStudent;
        } catch (Exception e) {
            logger.error("添加学生时发生错误: {}", e.getMessage(), e);
            throw new RuntimeException("添加学生失败", e);
        }
    }
    
    /**
     * 更新学生完成状态
     * @param id 学生ID
     * @return 更新后的学生对象
     */
    public Student updateStudentStatus(Long id) {
        logger.info("开始更新学生状态，学生ID: {}", id);
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();
                // 取反完成状态
                student.setIsCompleted(!student.getIsCompleted());
                Student updatedStudent = studentRepository.save(student);
                logger.info("成功更新学生状态，学生ID: {}, 新状态: {}", id, updatedStudent.getIsCompleted());
                return updatedStudent;
            } else {
                logger.warn("未找到ID为 {} 的学生", id);
                throw new RuntimeException("学生不存在");
            }
        } catch (Exception e) {
            logger.error("更新学生状态时发生错误: {}", e.getMessage(), e);
            throw new RuntimeException("更新学生状态失败", e);
        }
    }
    
    /**
     * 删除学生
     * @param id 学生ID
     * @return 删除操作结果
     */
    public boolean deleteStudent(Long id) {
        logger.info("开始删除学生，学生ID: {}", id);
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()) {
                studentRepository.deleteById(id);
                logger.info("成功删除学生，学生ID: {}", id);
                return true;
            } else {
                logger.warn("未找到ID为 {} 的学生", id);
                return false;
            }
        } catch (Exception e) {
            logger.error("删除学生时发生错误: {}", e.getMessage(), e);
            throw new RuntimeException("删除学生失败", e);
        }
    }
    
    /**
     * 根据ID获取学生信息
     * @param id 学生ID
     * @return 学生对象
     */
    public Student getStudentById(Long id) {
        logger.info("开始查询学生信息，学生ID: {}", id);
        try {
            Optional<Student> optionalStudent = studentRepository.findById(id);
            if (optionalStudent.isPresent()) {
                logger.info("成功查询到学生信息，学生ID: {}", id);
                return optionalStudent.get();
            } else {
                logger.warn("未找到ID为 {} 的学生", id);
                throw new RuntimeException("学生不存在");
            }
        } catch (Exception e) {
            logger.error("查询学生信息时发生错误: {}", e.getMessage(), e);
            throw new RuntimeException("查询学生信息失败", e);
        }
    }
} 