package com.amy.service_security.util.enumerators;

public enum UnmEnviroment 
{
    PRD("https://prod.amy.com:1088/"), 
    SIT("https://sit.amy.com:2019/"), 
    CIT("https://cit.amy.com:8080/"), 
    DEV("https://dev.amy.com:2132/");
 
    private String url;
 
    UnmEnviroment(String envUrl) {
        this.url = envUrl;
    }
 
    public String getUrl() {
        return url;
    }
}