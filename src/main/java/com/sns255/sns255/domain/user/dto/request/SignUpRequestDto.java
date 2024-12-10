package com.sns255.sns255.domain.user.dto.request;

import com.sns255.sns255.domain.user.entity.Department;
import com.sns255.sns255.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignUpRequestDto(

        @NotBlank(message = "이름은 필수 입력 사항입니다.")
        @Schema(description = "회원의 이름", example = "홍길동")
        String name,

        @NotBlank(message = "학번은 필수 입력 사항입니다.")
        @Pattern(regexp = "^\\d{9}$", message = "학번은 9자리 숫자여야 합니다.")
        @Schema(description = "회원의 학번", example = "202300001")
        String studentId,

        @NotBlank(message = "닉네임은 필수 입력 사항입니다.")
        @Schema(description = "회원의 닉네임", example = "uni")
        String anonymousName,

        @NotBlank(message = "이메일은 필수 입력 사항입니다.")
        @Schema(description = "회원의 이메일 주소", example = "user@example.com")
        @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
        @Schema(description = "회원의 비밀번호", example = "userPassword1!")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$", message = "비밀번호는 8~16자의 영문 대문자, 소문자, 숫자, 특수문자를 모두 포합해야합니다.")
        String password,

        @NotBlank(message = "비밀번호 확인은 필수 입력 사항입니다.")
        @Schema(description = "회원의 비밀번호 확인", example = "userPassword1!")
        String checkPassword,

        @NotBlank(message = "학과는 필수 입력 사항입니다.")
        @Schema(description = "회원의 학과", example = "컴퓨터공학과")
        String department
) {
    public User toEntity(String encodedPassword, Department department) {
        return User.builder()
                .name(name)
                .studentId(studentId)
                .anonymousName(anonymousName)
                .email(email)
                .password(encodedPassword)
                .department(department)
                .build();
    }
}
