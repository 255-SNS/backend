package com.sns255.sns255.domain.user.service;

import com.sns255.sns255.domain.user.entity.Verified;
import com.sns255.sns255.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCleanupScheduler {

    private final UserRepository userRepository;

    // 매일 새벽 3시에 실행 (cron: 초 분 시 일 월 요일)
    @Scheduled(cron = "0 0 3 * * ?")
    public void deleteUnverifiedUsers() {
        LocalDateTime expiryDate = LocalDateTime.now().minusDays(7); // 7일 초과된 사용자 삭제
        int deletedCount = userRepository.deleteUnverifiedUsers(Verified.PENDING, expiryDate);

        log.info("삭제된 인증되지 않은 사용자 수: {}", deletedCount);
    }
}
