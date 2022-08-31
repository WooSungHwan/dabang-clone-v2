package com.blackdog.dabang.common.error;

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

    private ErrorResponse(String code, String message, List<String> messages) {
        this.code = code;
        this.message = message;
        this.messages = messages;
        this.responseTime = LocalDateTime.now();
    }

    public static ErrorResponse of (String code, String message, List<String> messages) {
        return new ErrorResponse(code, message, messages);
    }

    public static ErrorResponse of (String code, String message) {
        return new ErrorResponse(code, message, null);
    }

    public static ErrorResponse of (ErrorCode errorCode) {
        return ErrorResponse.of(errorCode.getCode(), errorCode.getMessage());
    }
}
