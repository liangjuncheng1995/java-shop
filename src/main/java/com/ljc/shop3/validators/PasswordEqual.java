package com.ljc.shop3.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented //让注解里面的注释加入到文档里面
@Retention(RetentionPolicy.RUNTIME) //注解需要保留在什么阶段，将保留到运行的阶段
@Target({ElementType.TYPE, ElementType.FIELD}) //指定注解可以用在 哪个目标上面,比如类上面，方法上面
@Constraint(validatedBy = PasswordValidator.class) //注入自定义注解的关联类

public @interface PasswordEqual {
    int min() default 4;
    int max() default 6;

    String message() default "passwords are not equal";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {}; //规范两段
    //关联类 编程模式
}
