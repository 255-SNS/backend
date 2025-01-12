package com.sns255.sns255.domain.report.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReportRequestDto {

    @NotNull(message = "신고자 ID는 필수입니다.")
    private Long reporterId;

    @NotNull(message = "신고 대상 ID는 필수입니다.")
    private Long reportedUserId;

    @NotBlank(message = "신고 사유는 필수입니다.")
    private String reason;

    private String description; // 선택 사항

    public ReportRequestDto(Long reporterId, Long reportedUserId, String reason, String description) {
        this.reporterId = reporterId;
        this.reportedUserId = reportedUserId;
        this.reason = reason;
        this.description = description;
    }
}