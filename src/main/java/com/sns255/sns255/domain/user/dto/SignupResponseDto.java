package com.sns255.sns255.domain.user.dto;

import com.sns255.sns255.domain.user.entity.User;

public record SignupResponseDto (
    String name,
    int studentId,
    String email,
    String department,
    String team
) { public static SignupResponseDto fromEntity(User user) {
        return new SignupResponseDto (
            user.getName(),
            user.getStudentId(),
            user.getEmail(),
            user.getDepartment().name(),
            user.getTeam().name()
        );
    }
}
