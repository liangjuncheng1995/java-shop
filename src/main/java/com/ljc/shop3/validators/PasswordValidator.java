package com.ljc.shop3.validators;

import com.ljc.shop3.dto.PersionDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersionDTO> { //泛型传入的第二个参数：自定义注解修饰的目标的类型
    private int min;
    private int max;

    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(PersionDTO persionDTO, ConstraintValidatorContext constraintValidatorContext) {
        String password1 = persionDTO.getPassword1();
        String password2 = persionDTO.getPassword2();
        boolean match = password1.equals(password2);
        return match;
    }
}

