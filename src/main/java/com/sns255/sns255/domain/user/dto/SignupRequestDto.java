package com.sns255.sns255.domain.user.dto;

import com.sns255.sns255.domain.user.entity.Department;
import com.sns255.sns255.domain.user.entity.User;

public record SignupRequestDto (
        String name,
        int studentId,
        String email,
        String password,
        String department
) {
    public User toEntity(String encodedPassword, Department department) {
        return new User(name, studentId, email, encodedPassword, department);
    }
}