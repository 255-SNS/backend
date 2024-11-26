package com.sns255.sns255.domain.user.service;

import com.sns255.sns255.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //회원가입
    //정보 받기 -> 이메일 중복 확인 -> 학번 중복 확인 -> 팀 배정 -> 저장
}
