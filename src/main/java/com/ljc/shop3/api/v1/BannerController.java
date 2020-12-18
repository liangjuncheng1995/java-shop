package com.ljc.shop3.api.v1;

import com.ljc.shop3.exception.http.ForbiddenException;
import com.ljc.shop3.exception.http.NotFoundException;
import com.ljc.shop3.sample.IConnect;
import com.ljc.shop3.sample.ISkill;
import com.ljc.shop3.sample.hero.Diana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
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

    @GetMapping("/test")
    public String test() throws Exception{
        iSkill.r();
//        throw new NotFoundException(10001);

        throw new ForbiddenException(10001);
//        throw new Exception("SB");
//        throw new RuntimeException("sds");
//        return "hello  JAVA";
    }

//    @GetMapping("/test1")
//    public void test1() {
//        iConnect.connect();
//    }
}
