package com.blackdog.dabang.common.success;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Value;

@Value(staticConstructor = "of")
public class RestResponse<T> {

    private String message;

    private T result;

    @JsonFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime responseTime;

}
