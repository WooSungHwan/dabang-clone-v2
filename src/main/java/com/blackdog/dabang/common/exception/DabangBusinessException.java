package com.blackdog.dabang.common.exception;

import com.blackdog.dabang.common.response.error.ErrorCode;

public class DabangBusinessException extends DabangBaseException {
    public DabangBusinessException() {
        super(ErrorCode.BAD_REQUEST);
    }

    public DabangBusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DabangBusinessException(String errorMsg) {
        super(errorMsg, ErrorCode.BAD_REQUEST);
    }

    public DabangBusinessException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
