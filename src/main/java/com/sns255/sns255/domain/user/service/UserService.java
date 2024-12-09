package com.sns255.sns255.domain.user.service;

import com.sns255.sns255.domain.user.dto.request.SignUpRequestDto;

public interface UserService {
    UserResponseDto signUp(SignUpRequestDto signUpReqDto);
    //UserResponseDto signIn(SignInRequestDto signInReqDto);
}
