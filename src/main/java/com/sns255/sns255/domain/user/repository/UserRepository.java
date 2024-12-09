package com.sns255.sns255.domain.user.repository;

import com.sns255.sns255.domain.user.entity.Team;
import com.sns255.sns255.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자 검색
    Optional<User> findByEmail(String email);
    // 이메일 중복 확인
    boolean existsByEmail(String email);
    // 학번 중복 확인
    boolean existsByStudentId(int studentId);
    // 팀별 사용자 수 계산
    @Query("SELECT COUNT(u) FROM User u WHERE u.team = :team")
    long countByTeam(Team team);
}
