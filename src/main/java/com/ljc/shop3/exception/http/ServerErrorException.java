package com.ljc.shop3.exception.http;

public class ServerErrorException extends HttpException{
    public ServerErrorException(int code) {
        this.code = code;
        this.httpStatusCode = 500;
    }

}
