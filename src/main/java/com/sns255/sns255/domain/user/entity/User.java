package com.sns255.sns255.domain.user.entity;

import com.sns255.sns255.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // 컬럼 이름이 다르므로 유지
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String studentId;

    private String anonymousName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String department;

    private String team;

    private int points;

    private int totalPoints;

    private String image;

    protected User() {
    }
}
