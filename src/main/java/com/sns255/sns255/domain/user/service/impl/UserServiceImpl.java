package com.sns255.sns255.domain.user.service.impl;

import com.sns255.sns255.domain.user.dto.request.SignUpRequestDto;
import com.sns255.sns255.domain.user.dto.response.UserResponseDto;
import com.sns255.sns255.domain.user.entity.Department;
import com.sns255.sns255.domain.user.entity.Team;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.repository.UserRepository;
import com.sns255.sns255.domain.user.service.UserService;
import com.sns255.sns255.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.sns255.sns255.global.common.StatusCode.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto signUp(SignUpRequestDto signUpRequest) {
        existsByEmail(signUpRequest.email());

        // 비밀번호 일치 여부 확인
        if (!(signUpRequest.password().equals(signUpRequest.checkPassword())))
            throw new CustomException(PASSWORD_NOT_MATCH);

        String encodedPassword = passwordEncoder.encode(signUpRequest.password());

        // 팀 배정 (균형 유지)
        long blueCount = userRepository.countByTeam(Team.BLUE);
        long greenCount = userRepository.countByTeam(Team.GREEN);
        long redCount = userRepository.countByTeam(Team.RED);
        Team assignedTeam = Team.getBalancedTeam(blueCount, greenCount, redCount);

        // User 엔티티 생성 및 저장
        User user = signUpRequest.toEntity(encodedPassword, Department.valueOf(signUpRequest.department().toUpperCase()));
        user.assignTeam(assignedTeam);
        user = userRepository.save(user);

        // 응답 DTO 반환
        return UserResponseDto.toDto(user);
    }


    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(USER_ALREADY_EXISTS);
        }
    }

    private void existsByStudentId(Integer studentId) {
        if (userRepository.existsByStudentId(studentId)) {
            throw new CustomException(STUDENT_ID_ALREADY_EXISTS);
        }
    }
}
