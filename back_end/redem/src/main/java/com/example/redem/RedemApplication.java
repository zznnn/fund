package com.example.redem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.example.redem.mapper")
public class RedemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedemApplication.class, args);
	}

}
