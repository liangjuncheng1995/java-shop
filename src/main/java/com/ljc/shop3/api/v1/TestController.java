package com.ljc.shop3.api.v1;


import com.ljc.shop3.sample.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    //    @Autowired TestService testService;

    @Autowired
    private Test test;
//    private ObjectFactory<Test> test;

//    @Autowired
//    private Test test1;

    @GetMapping("/test")
    public void getDetail() {
//        System.out.println(this.test.getObject());
        System.out.println(this.test);
//        System.out.println(this.test1);
    }


//    public TestModel test() {
//        return testService.testService();
//    }
}
