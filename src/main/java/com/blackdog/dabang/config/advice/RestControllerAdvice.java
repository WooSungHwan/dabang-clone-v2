package com.blackdog.dabang.config.advice;

import com.blackdog.dabang.common.exception.DabangBaseException;
import com.blackdog.dabang.common.response.error.ErrorCode;
import com.blackdog.dabang.common.response.error.ErrorResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        return ErrorResponse.of(e.getErrorCode(), List.of(e.getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessages = getErrorMessages(e);
        return ErrorResponse.of(ErrorCode.INVALID_PARAMETER, errorMessages);
    }

    private List<String> getErrorMessages(MethodArgumentNotValidException e) {
        List<String> errorMessages = Optional.of(e.getBindingResult().getAllErrors()).orElse(Collections.emptyList())
                                             .stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return errorMessages;
    }

}
