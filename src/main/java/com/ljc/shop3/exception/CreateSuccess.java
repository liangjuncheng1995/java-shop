package com.ljc.shop3.exception;

import com.ljc.shop3.exception.http.HttpException;

public class CreateSuccess extends HttpException {
    public CreateSuccess(int code) {
        this.httpStatusCode = 201;
        this.code = code;
    }
}
