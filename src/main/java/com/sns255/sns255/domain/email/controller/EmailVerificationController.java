package com.sns255.sns255.domain.email.controller;

import com.sns255.sns255.domain.email.service.EmailVerificationService;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService verificationService;
    private final UserRepository userRepository;

    @PostMapping("/send/{userId}")
    public ResponseEntity<String> sendVerificationEmail(@PathVariable Long userId) throws MessagingException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        verificationService.sendVerificationEmail(user);
        return ResponseEntity.ok("이메일 인증 링크가 전송되었습니다.");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        verificationService.verifyToken(token);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }
}
