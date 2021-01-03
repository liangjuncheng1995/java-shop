package com.ljc.shop3.exception;

import com.ljc.shop3.exception.http.HttpException;

public class UpdateSuccess extends HttpException {
    public UpdateSuccess(int code) {
        this.httpStatusCode = 200;
        this.code = code;
    }

//    200 201 204 200
//    200 201 200 200
//    create 201
//    Get  200
//    Put : 200
//    Delete : 200 No Content
}
