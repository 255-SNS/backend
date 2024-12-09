package com.sns255.sns255.domain.user.controller;

import com.sns255.sns255.domain.user.dto.request.SignUpRequestDto;
import com.sns255.sns255.domain.user.dto.response.UserResponseDto;
import com.sns255.sns255.domain.user.service.UserService;
import com.sns255.sns255.global.common.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sns255.sns255.global.common.StatusCode.SIGN_UP;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<CommonResponse<UserResponseDto>> signUp(@RequestBody @Valid SignUpRequestDto signUpReqDto) {
        return ResponseEntity.status(SIGN_UP.getStatus())
                .body(CommonResponse.from(SIGN_UP.getMessage(),userService.signUp(signUpReqDto)));
    }

}
