package com.blackdog.dabang.common.exception;

import com.blackdog.dabang.common.response.error.ErrorCode;

public class InvalidStatusException extends DabangBaseException {
    public InvalidStatusException() {
        super(ErrorCode.INVALID_STATUS);
    }

    public InvalidStatusException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidStatusException(String errorMsg) {
        super(errorMsg, ErrorCode.INVALID_STATUS);
    }

    public InvalidStatusException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
