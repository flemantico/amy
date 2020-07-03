package com.amy.config_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ConfigEurekaServer
@SpringBootApplication
public class ConfigEureka {

	public static void main(String[] args) {
		SpringApplication.run(ConfigEureka.class, args);
	}
}

