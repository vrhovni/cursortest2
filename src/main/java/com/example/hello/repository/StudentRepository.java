package com.example.hello.repository;

import com.example.hello.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生数据访问层接口
 * 继承JpaRepository，提供基本的CRUD操作
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    /**
     * 根据完成状态查询学生列表
     * @param isCompleted 完成状态
     * @return 学生列表
     */
    List<Student> findByIsCompleted(Boolean isCompleted);
    
    /**
     * 根据学生信息内容模糊查询
     * @param value 学生信息内容
     * @return 学生列表
     */
    List<Student> findByValueContaining(String value);
    
    /**
     * 查询所有学生并按创建时间降序排列
     * @return 学生列表
     */
    List<Student> findAllByOrderByCreatedAtDesc();
} 