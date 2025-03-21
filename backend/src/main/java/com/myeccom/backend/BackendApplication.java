package com.myeccom.backend;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BackendApplication {
	private static final Logger logger = LoggerFactory.getLogger(BackendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@PostConstruct
	public void logCredentials() {
		logger.info("DB_USERNAME: {}", System.getenv("DB_USERNAME"));
		logger.info("DB_PASSWORD: {}", System.getenv("DB_PASSWORD"));
	}

}
