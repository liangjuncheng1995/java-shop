package com.ljc.shop3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ljc")
public class Shop3Application {

	public static void main(String[] args) {
		SpringApplication.run(Shop3Application.class, args);
		System.out.println("重启成功");
		//发现，扫描注解，没有注册的过程，简化注册的过程，缺点，代码不容易理顺
	}

}
