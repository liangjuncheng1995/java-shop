package com.ljc.shop3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ljc")
public class Shop3Application {

	public static void main(String[] args) {
		SpringApplication.run(Shop3Application.class, args);
	}

}
