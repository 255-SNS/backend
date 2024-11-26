package com.sns255.sns255.domain.user.entity;

import com.sns255.sns255.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    private String name;

    @Column(nullable = false, unique = true, name = "studentId")
    private String studentId;

    @Column(name = "annoymousName")
    private String anonymousName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Team team;

    private int points;

    private int totalPoints;

    private String image;

    @Enumerated(EnumType.STRING)
    private Verified isVerified = Verified.PENDING;

    protected User() {
    }
}
