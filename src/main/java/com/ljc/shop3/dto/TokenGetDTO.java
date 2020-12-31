package com.ljc.shop3.dto;

import com.ljc.shop3.core.enumeration.LoginType;
import com.ljc.shop3.dto.validators.TokenPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenGetDTO {
    @NotBlank(message = "account不允许为空")
    private String account;
    @TokenPassword(min=6, max=30, message = "{token.password}")
    private String password;

    private LoginType type;
}
