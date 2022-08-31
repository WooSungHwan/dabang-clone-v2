package com.blackdog.dabang.common.response.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Value;

@Value
public class ErrorResponse {
    private String code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> messages;

    @JsonFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime responseTime;

    private ErrorResponse(ErrorCode errorCode, List<String> messages) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.messages = messages;
        this.responseTime = LocalDateTime.now();
    }

    public static ErrorResponse of (ErrorCode errorCode, List<String> messages) {
        return new ErrorResponse(errorCode, messages);
    }

    public static ErrorResponse of (ErrorCode errorCode) {
        return new ErrorResponse(errorCode, null);
    }
}
