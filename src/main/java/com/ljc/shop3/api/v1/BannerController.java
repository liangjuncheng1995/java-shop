package com.ljc.shop3.api.v1;

import com.ljc.shop3.dto.PersionDTO;
import com.ljc.shop3.exception.http.ForbiddenException;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.sample.IConnect;
import com.ljc.shop3.sample.ISkill;
import com.ljc.shop3.sample.hero.Diana;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.Map;

@RestController
@RequestMapping("/banner")
@Validated
public class BannerController {

    @Autowired() //方便
//    @Qualifier("diana")
    private ISkill iSkill; //属性变量注入

//    @Autowired
//    public BannerController(Diana diana) { //构造函数注入(推荐)
//        this.diana = diana;
//    }

//    @Autowired
//    public void setDiana(Diana diana) { //Setter注入
//        this.diana = diana;
//    }
//    @Autowired
//    private IConnect iConnect;

//    @GetMapping("/test/{id1}")
//    public String test(@PathVariable(name = "id1") Integer id, @RequestParam String name) throws Exception{
//        System.out.println(id);
//        System.out.println(name);
//        iSkill.r();
////        throw new NotFoundException(10001);
//
//        throw new ForbiddenException(10000);
////        throw new Exception("SB");
////        throw new RuntimeException("sds");
////        return "hello  成";
//    }
    @PostMapping("/test/{id}")
    public PersionDTO test(@PathVariable @Range(min = 1,max = 10,message = "范围超出了") Integer id,
                       @RequestParam @Length(min=8) String name,
                       @RequestBody @Validated PersionDTO person
    ) throws Exception{
        //需要装箱的过程
//        vo //返回前端的数据类型
//        bo //
//        lombok
        System.out.println(id);
        System.out.println(name);
//        System.out.println(person);
        iSkill.r();

//        PersionDTO dto = new PersionDTO();
//        dto.setName("name");
//        dto.setAge(12);
//        return dto;


//        dto.setAge(12);
//        dto.setName("name");

        PersionDTO dto = PersionDTO.builder()
                .name("cheng")
                .age(18)
                .build();

        return dto;

        //throw new NotFoundException(10001);

//        throw new ForbiddenException(10000);
    //        throw new Exception("SB");
    //        throw new RuntimeException("sds");
    //        return "hello  成";
    }



//    @GetMapping("/test1")
//    public void test1() {
//        iConnect.connect();
//    }
}
