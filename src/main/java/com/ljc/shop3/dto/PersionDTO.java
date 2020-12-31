package com.ljc.shop3.dto;


//import lombok.Getter;
//
//
//import lombok.*;
//
//@Getter
//@Setter //推荐
////@Data
////@AllArgsConstructor //有参的构造函数生成
//@NoArgsConstructor //无参的构造函数生成
//@RequiredArgsConstructor //参数标注了 @NonNull 注解，生成构造函数的注解
////equals hashCode toString
//public class PersionDTO {
//    //    final private String t; //final类型只有get方法
//    @NonNull //参数不能为空的注解
//    private String name;
//    private Integer age;
//}

//bug


import com.ljc.shop3.dto.validators.PasswordEqual;
import lombok.*;
import org.hibernate.validator.constraints.Length;

//@Setter //推荐
////@Data
//@AllArgsConstructor //有参的构造函数生成
//@NoArgsConstructor //无参的构造函数生成
//@RequiredArgsConstructor //参数标注了 @NonNull 注解，生成构造函数的注解
//equals hashCode toString
@Builder //调用方不用实例化使用，想实例化使用，需要添加对应的NoArgsConstructor和Setter注解
//@Getter
@Getter
@PasswordEqual(min=1, message = "密码不一样")
public class PersionDTO {
    //    final private String t; //final类型只有get方法
//    @NonNull //参数不能为空的注解
    @Length(min = 2, max = 10, message = "liangjuncheng")
    private String name;

//    @Max()
    private Integer age;

//    @Valid
//    private SchoolDTO schoolDTO;
//    自己实现一个注解

    private String password1;
    private String password2;

}

//数据传输对象
