package com.segmentfault.spring.cloud.lesson8.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

/**
 * Created by lizhuquan on 2018/8/24.
 */
@RestControllerAdvice(assignableTypes = DemoRestController.class)
public class DemoRestControllerAdvicer {

    @ExceptionHandler(TimeoutException.class)
    public Object faultTolerance(Throwable throwable) {
        return throwable.getMessage();
    }
}
