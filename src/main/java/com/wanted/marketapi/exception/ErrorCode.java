package com.wanted.marketapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "E001"),
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "유효성 검사 실패, 필드가 누락되었거나 형식이 잘못되었습니다.", "E002"),
    DUPLICATE_LOGIN_ID(HttpStatus.CONFLICT, "중복된 로그인 아이디입니다.", "E003"),
    WRONG_SQL_GRAMMER(HttpStatus.CONFLICT, "잘못된 SQL 문법입니다.", "E004"),
    NOT_SUPPORTED_HTTP_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 http 메서드 호출입니다.", "E005"),
    NULL_POINTER(HttpStatus.INTERNAL_SERVER_ERROR, "null값을 처리하지 못하였습니다.", "E006"),
    NOT_FOUND_USERID_FROM_ADD_PRODUCT(HttpStatus.INTERNAL_SERVER_ERROR, "상품등록 도중 에러가 발생하였습니다.(등록할 유저의 정보를 찾지 못함.)", "E007");

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;
}
