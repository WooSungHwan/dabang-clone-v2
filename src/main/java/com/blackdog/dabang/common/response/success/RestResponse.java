package com.blackdog.dabang.common.response.success;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    @JsonFormat(pattern = "yyyy.MM.dd hh:mm:ss")
    private LocalDateTime responseTime;

    public static <T> RestResponse of(T result) {
        return RestResponse.builder()
                .message("ok")
                .result(result)
                .responseTime(LocalDateTime.now())
                .build();
    }
}
