package com.ljc.shop3.sample.hero;

import com.ljc.shop3.sample.ISkill;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class Irelia implements ISkill {
    private String name;
    private Integer age;

//    public Irelia(String name, Integer age) {
//        this.name = name;
//        this.age = age;
//        System.out.println("hello Irelia");
//    }

    public void q() {
        System.out.println("Irelia Q");
    }

    public void w() {
        System.out.println("Irelia W");
    }

    public void e() {
        System.out.println("Irelia E");
    }

    public void r() {
        System.out.println("Irelia R");
    }
}
