package com.sns255.sns255.domain.user.dto.response;

import com.sns255.sns255.domain.user.entity.User;

public record UserResponseDto(
    String name,
    int studentId,
    String email,
    String department,
    String team
) { public static UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
            user.getName(),
            user.getStudentId(),
            user.getEmail(),
            user.getDepartment().name(),
            user.getTeam().name()
        );
    }
}
