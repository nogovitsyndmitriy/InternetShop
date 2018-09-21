package com.gmail.nogovitsyndmitriy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:db.properties"})
@ComponentScan(basePackages = {
        "com.gmail.nogovitsyndmitriy.service",
        "com.gmail.nogovitsyndmitriy.dao",
        "com.gmail.nogovitsyndmitriy.config",
        "com.gmail.nogovitsyndmitriy.controllers"})
public class AppConfig {


}

