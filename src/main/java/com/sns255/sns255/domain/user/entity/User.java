package com.sns255.sns255.domain.user.entity;

import com.sns255.sns255.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_deleted = falsex")
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    private String name;

    @Column(nullable = false, unique = true, name = "studentId")
    private Integer studentId;

    @Column(name = "anonymousName")
    private String anonymousName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @ToString.Exclude
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Team team;

    private Integer points;

    @Column(name = "totalPoints")
    private Integer totalPoints;

    private String image;

    @Enumerated(EnumType.STRING)
    private Verified isVerified = Verified.PENDING;

    public User() {
    }

    @Builder
    public User(String name, Integer studentId, String email, String password, Department department) {
        this.name = name;
        this.studentId = studentId;
        this.anonymousName = anonymousName;
        this.email = email;
        this.password = password;
        this.department = department;
        this.isVerified = Verified.PENDING;
    }

    public void assignTeam(Team team) {
        this.team = team;
    }
}
