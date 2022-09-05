package com.blackdog.dabang.common.exception;

import com.blackdog.dabang.common.response.error.ErrorCode;

public class InvalidParameterException extends DabangBaseException {

    public InvalidParameterException() {
        super(ErrorCode.INVALID_PARAMETER);
    }

    public InvalidParameterException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InvalidParameterException(String errorMsg) {
        super(errorMsg, ErrorCode.INVALID_PARAMETER);
    }

    public InvalidParameterException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
