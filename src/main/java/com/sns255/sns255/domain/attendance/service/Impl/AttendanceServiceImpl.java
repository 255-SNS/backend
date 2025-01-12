package com.sns255.sns255.domain.attendance.service.Impl;

import com.sns255.sns255.domain.attendance.service.AttendanceService;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final UserRepository userRepository;

    private static final int ATTENDANCE_POINTS = 10; // 출석 시 지급 포인트

    @Override
    @Transactional
    public void checkAttendance(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 이미 오늘 출석했는지 확인
        if (user.getLastAttendanceDate() != null && user.getLastAttendanceDate().equals(LocalDate.now())) {
            throw new IllegalArgumentException("오늘 이미 출석체크를 완료했습니다.");
        }

        // 출석 체크 및 포인트 추가
        user.attend(ATTENDANCE_POINTS);
        userRepository.save(user); // 변경 사항 저장
    }
}
