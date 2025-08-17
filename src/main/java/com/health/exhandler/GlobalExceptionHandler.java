package com.health.exhandler;


import com.health.domain.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception exception) {
        log.error("出现异常：{}", exception.getMessage());
        return Result.error(exception.getMessage());
    }

}
