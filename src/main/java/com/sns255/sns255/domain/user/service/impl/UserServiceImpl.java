package com.sns255.sns255.domain.user.service.impl;

import com.sns255.sns255.domain.user.dto.request.SignupRequestDto;
import com.sns255.sns255.domain.user.dto.response.SignupResponseDto;
import com.sns255.sns255.domain.user.entity.Department;
import com.sns255.sns255.domain.user.entity.Team;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //회원가입
    //정보 받기 -> 이메일 중복 확인 -> 학번 중복 확인 -> 팀 배정 -> 저장
    public SignupResponseDto registerUser(SignupRequestDto request) {
        if(userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email address already in use");
        }

        if(userRepository.existsByStudentId(request.studentId())) {
            throw new IllegalArgumentException("Student id already in use");
        }

        long blueCount = userRepository.countByTeam(Team.BLUE);
        long greenCount = userRepository.countByTeam(Team.GREEN);
        long redCount = userRepository.countByTeam(Team.RED);

        Team assignedTeam = Team.getBalancedTeam(blueCount, greenCount, redCount);

        String encodedPassword = new BCryptPasswordEncoder().encode(request.password());

        Department department = Department.valueOf(request.department());
        User user = request.toEntity(encodedPassword, department);
        user.assignTeam(assignedTeam);

        userRepository.save(user);

        return SignupResponseDto.fromEntity(user);
    }
}
