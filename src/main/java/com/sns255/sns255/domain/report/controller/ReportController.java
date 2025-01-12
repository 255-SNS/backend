package com.sns255.sns255.domain.report.controller;

import com.sns255.sns255.domain.report.dto.req.ReportRequestDto;
import com.sns255.sns255.domain.report.dto.res.ReportResponseDto;
import com.sns255.sns255.domain.report.entity.ReportStatus;
import com.sns255.sns255.domain.report.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ReportResponseDto> createReport(@RequestBody @Valid ReportRequestDto requestDto) {
        ReportResponseDto response = reportService.createReport(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ReportResponseDto>> getReportsByStatus(@RequestParam ReportStatus status) {
        return ResponseEntity.ok(reportService.getReportsByStatus(status));
    }

    @PutMapping("/{reportId}/status")
    public ResponseEntity<String> updateReportStatus(@PathVariable Long reportId,
                                                     @RequestParam ReportStatus status) {
        reportService.updateReportStatus(reportId, status);
        return ResponseEntity.ok("신고 상태가 업데이트되었습니다.");
    }
}
