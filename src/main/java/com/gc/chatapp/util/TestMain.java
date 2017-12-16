package com.gc.chatapp.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.gc.chatapp")
public class TestMain {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestMain.class, args);
	}
	
	
}