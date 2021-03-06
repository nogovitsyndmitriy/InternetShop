package com.gmail.nogovitsyndmitriy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({"classpath:database.properties", "classpath:application.properties", "classpath:message.properties"})
@ComponentScan(basePackages = {
        "com.gmail.nogovitsyndmitriy.service",
        "com.gmail.nogovitsyndmitriy.dao",
        "com.gmail.nogovitsyndmitriy.config",
        "com.gmail.nogovitsyndmitriy.controllers.handlers",
        "com.gmail.nogovitsyndmitriy.controllers",
        "com.gmail.nogovitsyndmitriy.controllers.validators"
})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}

