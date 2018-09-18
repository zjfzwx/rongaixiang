package com.dingshen.rongaixiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dingshen.rongaixiang.mapper")
public class RongaixiangApplication {

	public static void main(String[] args) {
		SpringApplication.run(RongaixiangApplication.class, args);
	}
}
