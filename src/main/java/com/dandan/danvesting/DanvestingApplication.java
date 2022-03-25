package com.dandan.danvesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DanvestingApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DanvestingApplication.class, args);
	}

}
