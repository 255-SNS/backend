package com.sns255.sns255.domain.user.dto;

import com.sns255.sns255.domain.user.entity.User;

public record SignupResponseDto (
    String name,
    int studentId,
    String email,
    String department,
    String team
) { public static SignupRequestDto fromEntity(User user) {
        return new SignupRequestDto(
            user.getName(),
            user.getStudentId(),
            user.getEmail(),
            user.getDepartment().name(),
            user.getTeam().name()
        );
    }
}
