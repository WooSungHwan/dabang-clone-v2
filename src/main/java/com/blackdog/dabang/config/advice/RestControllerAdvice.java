package com.blackdog.dabang.config.advice;

import com.blackdog.dabang.common.exception.DabangBaseException;
import com.blackdog.dabang.common.response.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public final class RestControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DabangBaseException.class)
    public ErrorResponse handlerDabangBaseException(DabangBaseException e) {
        return ErrorResponse.of(e.getErrorCode());
    }

}
