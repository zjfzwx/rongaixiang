package com.dingshen.rongaixiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.dingshen.rongaixiang.mapper")
public class RongaixiangApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RongaixiangApplication.class, args);
	}
}
