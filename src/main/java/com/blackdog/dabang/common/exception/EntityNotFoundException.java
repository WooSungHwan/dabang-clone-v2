package com.blackdog.dabang.common.exception;

import com.blackdog.dabang.common.response.error.ErrorCode;

public class EntityNotFoundException extends DabangBaseException {

    public EntityNotFoundException() {
        super(ErrorCode.INVALID_PARAMETER);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EntityNotFoundException(String errorMsg) {
        super(errorMsg, ErrorCode.INVALID_PARAMETER);
    }

    public EntityNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
