package com.ljc.shop3.exception.http;

public class UnAuthenticatedException extends HttpException{
    public UnAuthenticatedException(int code) {
        this.code = code;
        this.httpStatusCode =400;
    }
}
