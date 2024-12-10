package com.sns255.sns255.domain.user.service;

import com.sns255.sns255.domain.user.dto.request.SignUpRequestDto;
import com.sns255.sns255.domain.user.dto.response.UserResponseDto;

public interface UserService {
    UserResponseDto signUp(SignUpRequestDto signUpRequest);
    //UserResponseDto signIn(SignInRequestDto signInReqDto);
}
