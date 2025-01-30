package me.lhy.commons.handler;

import lombok.extern.slf4j.Slf4j;
import me.lhy.commons.util.Result;
import me.lhy.commons.exception.UserMarkedAsDeletedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.info("其他异常：{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public Result<Void> handleNoSuchElementException(NoSuchElementException e) {
        log.info("未找到数据：{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(UserMarkedAsDeletedException.class)
    public Result<Void> handleUserMarkedAsDeletedException(UserMarkedAsDeletedException e) {
        log.info("用户已被标记删除：{}", e.getMessage());
        return Result.fail(e.getMessage());
    }

}
