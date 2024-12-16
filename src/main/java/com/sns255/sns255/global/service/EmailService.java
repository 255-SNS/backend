package com.sns255.sns255.global.service;

public interface EmailService {
    void sendEmail(String toEmail, String title, String text);
    String generateVerificationToken();
}
