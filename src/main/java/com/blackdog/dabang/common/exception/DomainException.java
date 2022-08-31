package com.blackdog.dabang.common.exception;

import com.blackdog.dabang.common.response.error.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private ErrorCode errorCode;

    public DomainException() {
    }

    public DomainException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public DomainException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DomainException(String message, ErrorCode errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
