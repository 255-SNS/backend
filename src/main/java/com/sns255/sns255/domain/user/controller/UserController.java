package com.sns255.sns255.domain.user.controller;

import com.sns255.sns255.domain.user.dto.SignupRequestDto;
import com.sns255.sns255.domain.user.dto.SignupResponseDto;
import com.sns255.sns255.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users/{id}")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto request) {
        try {
            SignupResponseDto response = userService.registerUser(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
