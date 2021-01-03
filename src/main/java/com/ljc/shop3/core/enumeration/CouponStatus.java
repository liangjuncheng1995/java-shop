package com.ljc.shop3.core.enumeration;

import java.util.stream.Stream;

public enum CouponStatus {
    AVATlABLE(1, "可以使用未过期"),
    USED(2, "已经使用"),
    EXPIRED(3, "未使用已过期");


    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    CouponStatus(Integer value, String description) {
        this.value = value;
    }

    public static CouponStatus toType(int value) {
//        switch (value) {
//
//        }
        return Stream.of(CouponStatus.values())
                .filter(c->c.value == value)
                .findAny()
                .orElse(null);
    }
}
