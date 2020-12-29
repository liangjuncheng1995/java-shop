package com.ljc.shop3.optional;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void testOptional() {
        Optional<String> empty = Optional.empty();
//        Optional<String> t1 = Optional.of(null);
        Optional<String> t2 = Optional.ofNullable("a");
//        empty.get();
//        return empty;
//        String s =  t2.get();
//        t2.ifPresent(t-> System.out.println(t));
        t2.ifPresent(System.out::println);
//        空指针 潜在风险 函数调用栈 变深 溯源

//        String s;
//        if(t2) {
//            s = t2.get();
//        } else {
//            s = "默认值";
//        }
//        optional 的写法

        String s = t2.orElse("默认值");
        System.out.println(s);
//        String s1 = t2.map(t->t+"b").orElse("c");
//        System.out.println(s1);
        t2.map(t->t+"b").ifPresent(System.out::println);

//        t2.orElseGet()orElseGet
//        consumer supplier runnable function predicate
    }

}
