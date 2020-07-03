package com.amy.config_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ConfigEureka {

	public static void main(String[] args) {
		SpringApplication.run(ConfigEureka.class, args);
	}
}

