package com.sns255.sns255.domain.attendance.controller;

import com.sns255.sns255.domain.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check/{userId}")
    public ResponseEntity<String> checkAttendance(@PathVariable Long userId) {
        attendanceService.checkAttendance(userId);
        return ResponseEntity.ok("출석체크가 완료되었습니다. 포인트가 지급되었습니다.");
    }
}
