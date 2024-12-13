package com.sns255.sns255.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignInRequestDto {

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Schema(description = "회원의 이메일 주소", example = "user@inu.ac.kr")
    private String email;

    @NotBlank(message = "비빌번호는 필수 입력 사항입니다.")
    @Schema(description = "회원의 비밀번호", example = "userPassword!")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    private String password;
}
