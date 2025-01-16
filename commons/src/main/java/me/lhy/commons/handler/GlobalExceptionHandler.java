package me.lhy.commons.handler;

import lombok.extern.slf4j.Slf4j;
import me.lhy.commons.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.info("全局异常处理：{}", e.getMessage());
        return Result.fail(e.getMessage());
    }
}
