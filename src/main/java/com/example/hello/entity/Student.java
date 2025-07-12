package com.example.hello.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 学生实体类
 * 对应数据库中的学生信息表
 */
@Entity
@Table(name = "students")
public class Student {
    
    /**
     * 学生ID，主键，自动生成
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 学生信息内容，不能为空
     */
    @NotBlank(message = "学生信息内容不能为空")
    @Column(name = "value", nullable = false, length = 1000)
    private String value;
    
    /**
     * 是否完成状态，默认为false
     */
    @NotNull(message = "完成状态不能为空")
    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;
    
    /**
     * 创建时间，自动生成
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * 更新时间，自动更新
     */
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    // 默认构造函数
    public Student() {}
    
    // 带参数的构造函数
    public Student(String value, Boolean isCompleted) {
        this.value = value;
        this.isCompleted = isCompleted;
    }
    
    // Getter和Setter方法
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", isCompleted=" + isCompleted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
} 