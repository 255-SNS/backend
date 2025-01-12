package com.sns255.sns255.domain.report.dto.res;

import com.sns255.sns255.domain.report.entity.Report;
import com.sns255.sns255.domain.report.entity.ReportStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportResponseDto {

    private Long id;
    private Long reporterId;
    private Long reportedUserId;
    private String reason;
    private String description;
    private ReportStatus status;

    public static ReportResponseDto fromEntity(Report report) {
        return ReportResponseDto.builder()
                .id(report.getId())
                .reporterId(report.getReporter().getId())
                .reportedUserId(report.getReportedUser().getId())
                .reason(report.getReason())
                .description(report.getDescription())
                .status(report.getStatus())
                .build();
    }
}