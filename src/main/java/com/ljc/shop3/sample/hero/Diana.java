package com.ljc.shop3.sample.hero;

import com.ljc.shop3.sample.ISkill;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component //没有办法对私有的属性进行赋值
//@Primary //优先
//@Lazy //延迟实例化
public class Diana implements ISkill {
    private String skillName = "Diana R";

    private String name;
    private Integer age;

    public Diana(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("hello,Diana");
    }



    public void q() {
        System.out.println("Diana Q");
    }

    public void w() {
        System.out.println("Diana W");
    }

    public void e() {
        System.out.println("Diana E");
    }

    public void r() {
        System.out.println(this.skillName);
    }
}
