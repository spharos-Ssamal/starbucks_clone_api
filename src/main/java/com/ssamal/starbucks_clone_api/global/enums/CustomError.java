package com.ssamal.starbucks_clone_api.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomError {
    //BAD_REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청이 올바르지 않습니다.", "ERROR-BR-000"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다.","ERROR-BR-001"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "올바르지 않은 토큰 요청입니다.", "ERROR-BR-002"),

    //UNAUTHORIZED
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.", "ERROR-UA-000"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Token Expired", "ERROR-UA-001"),
    //NOT_FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND, "해당 요청 정보를 찾을 수 없습니다.", "ERROR-NF-000"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저의 정보를 찾을 수 없습니다.", "ERROR-NF-001"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 상품 정보를 찾을 수 없습니다.", "ERROR-NF-002"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카테고리 정보를 찾을 수 없습니다.", "ERROR-NF-003"),
    HASH_TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 해시태그 정보를 찾을 수 없습니다.", "ERROR-NF-004"),
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 이벤트 정보를 찾을 수 없습니다.", "ERROR-NF-005"),
    SEASON_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 시즌 정보를 찾을 수 없습니다.", "ERROR-NF-006"),
    RECOMMAND_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 추천 상품 정보를 찾을 수 없습니다.", "ERROR-NF-007"),
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 주소를 찾을 수 없습니다.", "ERROR-NF-008"),

    //FORBIDDEN
    FORBIDDEN(HttpStatus.FORBIDDEN,  "금지된 요청입니다.", "ERROR-FB-000"),
    REFRESH_TOKEN_EXPIRED(HttpStatus.FORBIDDEN, "리프레쉬 토큰이 만료되었습니다.", "ERROR-FB-001"),

    //CONFLICT
    CONFLICT(HttpStatus.CONFLICT, "해당 요청에 대해 충돌이 일어났습니다.", "ERROR-CF-000"),
    DUPLICATED_USER(HttpStatus.CONFLICT, "이미 해당 유저가 존재합니다.", "ERROR-CF-001"),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "이미 사용중인 유저 아이디입니다.", "ERROR-CF-002"),
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "이미 사용중인 유저 이메일입니다.", "ERROR-CF-003"),
    DUPLICATED_USER_NICKNAME(HttpStatus.CONFLICT, "이미 사용중인 유저 닉네임입니다.", "ERROR-CF-004"),
    DUPLICATED_PRODUCT_NAME(HttpStatus.CONFLICT, "이미 등록 된 상품입니다.", "ERROR-CF-005"),
    DUPLICATED_CATEGORY_NAME(HttpStatus.CONFLICT, "이미 등록 된 카테고리 이름입니다.", "ERROR-CF-006"),
    DUPLICATED_EVENT_NAME(HttpStatus.CONFLICT, "이미 등록 된 이벤트 입니다.", "ERROR-CF-007"),
    DUPLICATED_SEASON_NAME(HttpStatus.CONFLICT, "이미 등록 된 시즌 입니다.", "ERROR-CF-008"),
    DUPLICATED_HASHTAG_NAME(HttpStatus.CONFLICT, "이미 등록 된 해시태그 입니다.", "ERROR-CF-009"),
    DUPLICATED_RECOMMEND_NAME(HttpStatus.CONFLICT, "이미 등록 된 추천 입니다.", "ERROR-CF-010"),
    INVALID_EMAIL_VERIFICATION_CODE(HttpStatus.CONFLICT, "이메일 인증 코드를 잘못 입력 하셨습니다.", "ERROF-CF-011"),

    //INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,  "내부 서버 오류입니다. 관리자에게 문의하세요", "ERROR-ISE-000");

    private final HttpStatus httpStatus;
    private final String message;
    private final String errorCode;
}
