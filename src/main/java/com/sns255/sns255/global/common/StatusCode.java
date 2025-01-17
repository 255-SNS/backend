package com.sns255.sns255.global.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.CONFLICT;

@Getter
@RequiredArgsConstructor
public enum StatusCode {

    /* User */
    SIGN_UP(CREATED, "회원가입이 완료되었습니다."),
    SIGN_IN(OK, "로그인에 성공하였습니다."),

    /* 400 BAD_REQUEST : 잘못된 요청 */
    PASSWORD_NOT_MATCH(BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    /* 401 UNAUTHORIZED : 비인증 사용자 */
    AUTHENTICATION_FAILED(UNAUTHORIZED, "회원의 정보가 일치하지 않습니다."),

    /* 403 FORBIDDEN : 권한 없음 */
    VERIFIED_FAILED(UNAUTHORIZED, "이메일 인증이 완료되지 않았습니다."),

    /* 404 NOT_FOUND : 존재하지 않는 리소스 */
    INPUT_VALUE_INVALID(NOT_FOUND, "유효하지 않은 입력입니다."),
    USER_NOT_FOUND(NOT_FOUND, "존재하는 회원이 없습니다."),

    /* 409 CONFLICT : 리소스 충돌 */
    USER_ALREADY_EXISTS(CONFLICT, "이미 존재하는 회원입니다."),
    STUDENT_ID_ALREADY_EXISTS(CONFLICT, "이미 존재하는 회원입니다.");

    private final HttpStatus status;
    private final String message;
}