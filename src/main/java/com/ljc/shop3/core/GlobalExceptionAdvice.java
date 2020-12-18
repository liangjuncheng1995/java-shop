package com.ljc.shop3.core;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class) //异常处理器
    public void handleException(HttpServletRequest req, Exception e) {
        System.out.println("hello");
    }
}