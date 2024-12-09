package com.sns255.sns255.domain.user.service.impl;

import com.sns255.sns255.domain.user.dto.request.SignUpRequestDto;
import com.sns255.sns255.domain.user.dto.response.UserResponseDto;
import com.sns255.sns255.domain.user.entity.Department;
import com.sns255.sns255.domain.user.entity.Team;
import com.sns255.sns255.domain.user.entity.User;
import com.sns255.sns255.domain.user.repository.UserRepository;
import com.sns255.sns255.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.sns255.sns255.global.common.StatusCode.PASSWORD_NOT_MATCH;

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

        String enCodePassword = passwordEncoder.encode(signUpReqDto.getPassword());

        User user = userRepository.save(signUpReqDto.toEntity(signUpReqDto.getEmail(), enCodePassword));
        return UserResDto.toDto(user);
    }

    @Override
    public UserResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        // 이메일 중복 확인
        existsByEmail(signUpRequestDto.getEmail());

        // 학번 중복 확인
        existsByStudentId(signUpRequestDto.getStudentId());

        // 비밀번호 일치 여부 확인
        if (!signUpRequestDto.getPassword().equals(signUpRequestDto.getCheckPassword())) {
            throw new CustomException(PASSWORD_NOT_MATCH);
        }

        // 비밀번호 암호화
        String enCodePassword = passwordEncoder.encode(signUpReqDto.getPassword());

        // 팀 배정 (균형 유지)
        long blueCount = userRepository.countByTeam(Team.BLUE);
        long greenCount = userRepository.countByTeam(Team.GREEN);
        long redCount = userRepository.countByTeam(Team.RED);
        Team assignedTeam = Team.getBalancedTeam(blueCount, greenCount, redCount);

        // User 엔티티 생성 및 저장
        User user = signUpReqDto.toEntity(enCodePassword, Department.valueOf(signUpReqDto.getDepartment().toUpperCase()));
        user.assignTeam(assignedTeam);
        user = userRepository.save(user);

        // 응답 DTO 반환
        return UserResDto.toDto(user);
    }

    private void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(EMAIL_ALREADY_EXISTS);
        }
    }

    private void existsByStudentId(Integer studentId) {
        if (userRepository.existsByStudentId(studentId)) {
            throw new CustomException(STUDENT_ID_ALREADY_EXISTS);
        }
    }
}
