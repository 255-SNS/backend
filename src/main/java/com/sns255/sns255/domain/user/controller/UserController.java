package com.sns255.sns255.domain.user.controller;

import com.sns255.sns255.domain.user.dto.request.SignupRequestDto;
import com.sns255.sns255.domain.user.dto.response.SignupResponseDto;
import com.sns255.sns255.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto request) {
        try {
            SignupResponseDto response = userService.registerUser(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}