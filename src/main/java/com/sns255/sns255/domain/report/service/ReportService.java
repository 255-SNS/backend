package com.sns255.sns255.domain.report.service;

import com.sns255.sns255.domain.report.entity.Report;
import com.sns255.sns255.domain.report.entity.ReportStatus;
import com.sns255.sns255.domain.report.repository.ReportRepository;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createReport(Long reporterId, Long reportedUserId, String reason, String description) {
        User reporter = userRepository.findById(reporterId)
                .orElseThrow(() -> new IllegalArgumentException("신고자를 찾을 수 없습니다."));
        User reportedUser = userRepository.findById(reportedUserId)
                .orElseThrow(() -> new IllegalArgumentException("신고 대상을 찾을 수 없습니다."));

        Report report = new Report(reporter, reportedUser, reason, description);
        reportRepository.save(report);
    }

    public List<Report> getReportsByStatus(ReportStatus status) {
        return reportRepository.findByStatus(status);
    }

    @Transactional
    public void updateReportStatus(Long reportId, ReportStatus status) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("신고를 찾을 수 없습니다."));
        report.updateStatus(status);
    }
}
