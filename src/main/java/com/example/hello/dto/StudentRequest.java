package com.example.hello.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 学生请求数据传输对象
 * 用于接收前端传来的学生信息
 */
public class StudentRequest {
    
    /**
     * 学生信息内容
     */
    @NotBlank(message = "学生信息内容不能为空")
    private String value;
    
    /**
     * 是否完成状态，默认为false
     */
    @NotNull(message = "完成状态不能为空")
    private Boolean isCompleted = false;
    
    // 默认构造函数
    public StudentRequest() {}
    
    // 带参数的构造函数
    public StudentRequest(String value, Boolean isCompleted) {
        this.value = value;
        this.isCompleted = isCompleted;
    }
    
    // Getter和Setter方法
    
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
    
    @Override
    public String toString() {
        return "StudentRequest{" +
                "value='" + value + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
} 