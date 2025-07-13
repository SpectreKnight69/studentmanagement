package com.example.studentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
@EnableCaching
public class StudentmanagementApplication {

	public static void main(String[] args) {
		// Set the strategy before the application starts
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		SpringApplication.run(StudentmanagementApplication.class, args);
	}

}
