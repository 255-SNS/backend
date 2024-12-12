package com.sns255.sns255.domain.email.service;

import com.sns255.sns255.domain.user.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.transaction.annotation.Transactional;

public interface EmailVerificationService {
    @Transactional
    void sendVerificationEmail(User user) throws MessagingException;

    @Transactional
    void verifyToken(String token);
}
