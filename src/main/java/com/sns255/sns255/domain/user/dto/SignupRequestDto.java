package com.sns255.sns255.domain.user.dto;

public record SignupRequestDto (
        String name,
        int studentId,
        String email,
        String password,
        String department
) {}