package com.amy.config_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ConfigServiceApplication.class, args);
	}

}
