package com.sns255.sns255.global.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    String generateVerificationToken();
}
