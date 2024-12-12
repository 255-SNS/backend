package com.sns255.sns255.domain.user.controller;

import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.service.EmailVerificationService;
import com.sns255.sns255.global.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/email")
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;
    private final EmailService emailService;

    public EmailVerificationController(EmailVerificationService emailVerificationService, EmailService emailService) {
        this.emailVerificationService = emailVerificationService;
        this.emailService = emailService;
    }

    // 회원가입 후 이메일 인증 요청
    @PostMapping("/send")
    public ResponseEntity<String> sendVerificationEmail(@RequestBody User user) {
        String token = emailVerificationService.createVerificationToken(user);
        String verificationUrl = "http://localhost:8080/api/auth/email/verify?token=" + token;

        emailService.sendEmail(user.getEmail(), "Email Verification",
                "Click the link to verify your email: " + verificationUrl);

        return ResponseEntity.ok("Verification email sent to " + user.getEmail());
    }

    // 이메일 인증 링크 클릭
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        emailVerificationService.verifyToken(token);
        return ResponseEntity.ok("Email successfully verified!");
    }}
