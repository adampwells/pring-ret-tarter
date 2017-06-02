package com.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterApplication {

	private static final Logger log = LoggerFactory.getLogger(StarterApplication.class);

	public static void main(String[] args) {
		log.debug("Starting!");
		SpringApplication.run(StarterApplication.class, args);
	}
}
