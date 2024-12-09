package com.sns255.sns255.domain.user.dto.response;

import com.sns255.sns255.domain.user.entity.User;

public record UserResponseDto(
        String name,
        Integer studentId,
        String email,
        String department,
        String team
) {
    public static UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
                user.getName(),                 // Lombok @Getter로 자동 생성된 메서드
                user.getStudentId(),
                user.getEmail(),
                user.getDepartment().toString(), // Enum을 문자열로 변환
                user.getTeam().toString()
        );
    }
}
