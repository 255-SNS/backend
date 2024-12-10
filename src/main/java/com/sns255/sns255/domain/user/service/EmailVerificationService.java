package com.sns255.sns255.domain.user.service;

import com.sns255.sns255.domain.user.entity.User;

public interface EmailVerificationService {
    String createVerificationToken(User user);
    void verifyToken(String token);
}
