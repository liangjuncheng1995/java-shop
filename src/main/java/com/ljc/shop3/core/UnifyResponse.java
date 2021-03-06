package com.ljc.shop3.core;

import com.ljc.shop3.exception.CreateSuccess;

public class UnifyResponse {
    private int code;
    private String message;
    private String request;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    public UnifyResponse(int code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }

    public static void createSuccess(int code) {
        throw new CreateSuccess(code);
    }
}
