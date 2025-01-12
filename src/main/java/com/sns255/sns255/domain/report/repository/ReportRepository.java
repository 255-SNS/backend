package com.sns255.sns255.domain.report.repository;

import com.sns255.sns255.domain.report.entity.Report;
import com.sns255.sns255.domain.report.entity.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByStatus(ReportStatus status); // 상태별 신고 조회
}