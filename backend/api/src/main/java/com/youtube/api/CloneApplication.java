package com.youtube.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(
		scanBasePackages = {
				"com.youtube",
				"com.youtube.core"
		}
)
@EntityScan(basePackages = "com.youtube.core")
public class CloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneApplication.class, args);
	}

}
