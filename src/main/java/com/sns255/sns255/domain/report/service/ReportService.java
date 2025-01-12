package com.sns255.sns255.domain.report.service;

import com.sns255.sns255.domain.report.dto.req.ReportRequestDto;
import com.sns255.sns255.domain.report.dto.res.ReportResponseDto;
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
    public ReportResponseDto createReport(ReportRequestDto requestDto) {
        User reporter = userRepository.findById(requestDto.getReporterId())
                .orElseThrow(() -> new IllegalArgumentException("신고자를 찾을 수 없습니다."));
        User reportedUser = userRepository.findById(requestDto.getReportedUserId())
                .orElseThrow(() -> new IllegalArgumentException("신고 대상을 찾을 수 없습니다."));

        Report report = new Report(reporter, reportedUser, requestDto.getReason(), requestDto.getDescription());
        Report savedReport = reportRepository.save(report);

        return ReportResponseDto.fromEntity(savedReport);
    }

    public List<ReportResponseDto> getReportsByStatus(ReportStatus status) {
        return reportRepository.findByStatus(status)
                .stream()
                .map(ReportResponseDto::fromEntity)
                .toList();
    }

    @Transactional
    public void updateReportStatus(Long reportId, ReportStatus status) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("신고를 찾을 수 없습니다."));
        report.updateStatus(status);
    }
}
