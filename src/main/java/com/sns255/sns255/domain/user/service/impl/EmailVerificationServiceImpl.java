package com.sns255.sns255.domain.user.service.impl;

import com.sns255.sns255.domain.user.entity.EmailVerificationToken;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.entity.Verified;
import com.sns255.sns255.domain.user.repository.EmailVerificationTokenRepository;
import com.sns255.sns255.domain.user.repository.UserRepository;
import com.sns255.sns255.domain.user.service.EmailVerificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public EmailVerificationServiceImpl(EmailVerificationTokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public String createVerificationToken(User user) {
        // 기존 토큰 삭제 (사용자가 이미 인증 토큰을 가지고 있을 경우)
        tokenRepository.findByToken(user.getEmail())
                .ifPresent(tokenRepository::delete);

        // 새 토큰 생성
        String token = UUID.randomUUID().toString();
        EmailVerificationToken verificationToken = new EmailVerificationToken(token, user);
        tokenRepository.save(verificationToken);

        return token;
    }

    @Override
    @Transactional
    public void verifyToken(String token) {
        EmailVerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 토큰입니다."));

        // 토큰 만료 여부 확인
        if (verificationToken.isExpired()) {
            throw new IllegalArgumentException("토큰이 만료되었습니다.");
        }

        // 사용자 인증 상태 업데이트
        User user = verificationToken.getUser();
        user.checkVerified(Verified.VERIFIED);
        userRepository.save(user);

        // 인증이 완료되었으므로 토큰 삭제
        tokenRepository.delete(verificationToken);
    }
}
