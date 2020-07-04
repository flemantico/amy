package com.amy.security.util;

public class ApplicationConfig {}

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath: application.properties")
@ConfigurationProperties(prefix = "datos")
public class ApplicationConfig {

    @Value("name")
    private String value;

    @Autowired
    Enviroment env;


    public String getPropertie(String name){
        return env.getProperty(name);
    }
}
*/