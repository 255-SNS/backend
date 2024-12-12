package com.sns255.sns255.domain.email.service.impl;

import com.sns255.sns255.domain.email.entity.EmailVerificationToken;
import com.sns255.sns255.domain.email.repository.EmailVerificationTokenRepository;
import com.sns255.sns255.domain.email.service.EmailVerificationService;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.entity.Verified;
import com.sns255.sns255.domain.user.repository.UserRepository;
import com.sns255.sns255.global.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public void sendVerificationEmail(User user) throws MessagingException {
        // 기존 토큰 삭제
        tokenRepository.deleteById(user.getId());

        // 새로운 토큰 생성
        EmailVerificationToken token = new EmailVerificationToken(user);
        tokenRepository.save(token);

        // 이메일 전송
        String verificationLink = "http://localhost:8080/api/email/verify?token=" + token.getToken();
        String emailContent = "<p>이메일 인증 링크: <a href=\"" + verificationLink + "\">여기를 클릭하세요</a></p>";
        emailService.sendEmail(user.getEmail(), "SNS 이메일 인증", emailContent);
    }

    @Override
    @Transactional
    public void verifyToken(String token) {
        EmailVerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

        if (verificationToken.isExpired()) {
            throw new IllegalArgumentException("토큰이 만료되었습니다.");
        }

        User user = verificationToken.getUser();
        user.checkVerified(Verified.VERIFIED); // 인증 완료
        userRepository.save(user);

        tokenRepository.delete(verificationToken); // 사용 완료된 토큰 삭제
    }
}
