package com.ljc.shop3.sample.hero;

import com.ljc.shop3.sample.ISkill;


public class Camille implements ISkill {
    private String skillName = "Camille R";

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String name;
    private Integer age;

    public Camille(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("hello Camille");

    }

    public void q() {
        System.out.println("Camille Q");
    }

    public void w() {
        System.out.println("Camille W");
    }

    public void e() {
        System.out.println("Camille E");
    }

    public void r() {
        System.out.println("Camille R");
    }
}
