package com.sns255.sns255.domain.user.repository;

import com.sns255.sns255.domain.user.entity.Team;
import com.sns255.sns255.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByStudentId(int studentId);

    @Query("SELECT COUNT(u) FROM User u WHERE u.team = :team")
    long countByTeam(Team team);
}
