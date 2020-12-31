package com.ljc.shop3.core.enumeration;

public enum LoginType {
    USER_WX(0, "微信的登录"),
    USER_Email(1, "邮箱登录");

    private Integer value;
    private String description;

    LoginType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }
}
