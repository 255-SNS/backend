package com.sns255.sns255.domain.report.entity;

import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.global.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Report extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter; // 신고자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_user_id", nullable = false)
    private User reportedUser; // 신고 대상

    @Column(nullable = false)
    private String reason; // 신고 사유

    @Column(length = 1000)
    private String description; // 상세 내용

    @Enumerated(EnumType.STRING)
    private ReportStatus status = ReportStatus.PENDING; // 기본 상태는 PENDING

    public Report(User reporter, User reportedUser, String reason, String description) {
        this.reporter = reporter;
        this.reportedUser = reportedUser;
        this.reason = reason;
        this.description = description;
    }

    public void updateStatus(ReportStatus status) {
        this.status = status;
    }
}
