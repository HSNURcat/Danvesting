package com.dandan.danvesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DanvestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DanvestingApplication.class, args);
	}

}
