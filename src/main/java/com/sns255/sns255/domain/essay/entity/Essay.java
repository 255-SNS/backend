package com.sns255.sns255.domain.essay.entity;

import com.sns255.sns255.domain.board.entity.Board;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.global.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Essay extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "essay_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Column(nullable = false)
    private String company; // 기업 명

    @Column(nullable = false)
    private String schoolGrade; // 학교 성적

    @Column
    private String englishScore; // 영어 성적

    @Column(nullable = false)
    private String awards; // 수상 내역

    @Column
    private String certificates; // 자격증

    @Column(columnDefinition = "TEXT")
    private String projects; // 프로젝트(대내외) 경험

    @Column(nullable = false)
    private String fileUrl1; //업로드 한 자기소개서 파일 경로

    @Column(nullable = false)
    private String fileUrl2;

    protected Essay() {

    }
}
