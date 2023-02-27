package com.ssamal.starbucks_clone_api.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomError {
    //BAD_REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청이 올바르지 않습니다.", "ERROR-BR-001"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다.","ERROR-BR-002"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "올바르지 않은 토큰 요청입니다.", "ERROR-BR-003"),

    //UNAUTHORIZED
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Token Expired", "ERROR-UA-001"),

    //NOT_FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저의 정보를 찾을 수 없습니다.", "ERROR-NF-001"),

    //FORBIDDEN
    FORBIDDEN(HttpStatus.FORBIDDEN,  "금지된 요청입니다.", "ERROR-FB-001"),
    REFRESH_TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "리프레쉬 토큰이 만료되었습니다.", "ERROR-FB-002"),

    //CONFLICT
    DUPLICATE_USER(HttpStatus.CONFLICT, "이미 해당 유저가 존재합니다.", "ERROR-CF-001"),
    DUPLICATE_USER_NAME(HttpStatus.CONFLICT, "이미 사용중인 유저 아이디입니다.", "ERROR-CF-002"),
    DUPLICATE_USER_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 유저 이메일입니다.", "ERROR-CF-003"),
    DUPLICATE_USER_NICKNAME(HttpStatus.CONFLICT, "이미 사용중인 유저 닉네임입니다.", "ERROR-CF-004"),

    //INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  "내부 서버 오류입니다. 관리자에게 문의하세요", "ERROR-ISE-001");

    private final HttpStatus httpStatus;
    private final String message;
    private final String errorCode;
}
