package com.ljc.shop3.service;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        String str = list.get(0);
        System.out.println(str);

        Object obj = '1';
        Integer integer = (Integer) obj;
//        String str = (String)obj;
//        System.out.println(str);
    }
}
