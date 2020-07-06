package com.amy.service_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient // es lo mosmo que @EnableEurekaClient
//@EnableFeignClients
public class ServiceSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSecurityApplication.class, args);
	}

}
