package com.amy.serversecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
//@EnableAuthorizationServer
@EnableEurekaClient
//@EnableDiscoveryClient // es lo mosmo que @EnableEurekaClient
//@EnableFeignClients
public class ServerSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerSecurityApplication.class, args);
	}

}



/***TODO
 * Ver como usar https, con certificado ssl
 * siempre se debe usar https con un servidor certificado para desplegar.
 * 
 */