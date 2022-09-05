package com.blackdog.dabang.common.exception;

import com.blackdog.dabang.common.response.error.ErrorCode;
import lombok.Getter;

@Getter
public class DabangBaseException extends RuntimeException {
    private ErrorCode errorCode;

    public DabangBaseException() {
    }

    public DabangBaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DabangBaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DabangBaseException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
