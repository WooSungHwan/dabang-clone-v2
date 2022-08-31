package com.blackdog.dabang.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST("BAD_REQUEST", "잘못된 요청입니다."),
    JWT_BAD_REQUEST("JWT_BAD_REQUEST", "인증 정보를 확인해주시기 바랍니다."),
    USER_NOT_FOUND("USER_NOT_FOUND", "유저 정보가 없습니다."),
    STORE_NOT_FOUND("STORE_NOT_FOUND", "가게 정보가 없습니다."),
    KAKAO_USER_NOT_FOUND("KAKAO_USER_NOT_FOUND", "카카오 유저 정보가 없습니다."),
    LOGIN_EXPIRED("LOGIN_EXPIRED", "로그인이 만료되었습니다."),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "요청에 해당하는 리소스 정보가 없습니다."),
    RESOURCE_FORBIDDEN("RESOURCE_FORBIDDEN", "해당 리소스에 접근하실 수 없습니다."),
    FILE_UPLOADER_FAIL("FILE_UPLOADER_FAIL", "파일 업로드 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");

    private String code;

    private String message;
}
