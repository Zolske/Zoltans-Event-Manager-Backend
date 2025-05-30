package com.kepes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ZoltansEventManagerBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZoltansEventManagerBackendApplication.class, args);
	}

}
