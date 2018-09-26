package com.gmail.nogovitsyndmitriy.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
public class PageProperties {

    @Autowired
    private Environment environment;

    private String loginPagePath;
    private String usersPagePath;
    private String errorsPagePath;
    private String itemsPagePath;

    @PostConstruct
    public void initialize() {
        this.loginPagePath = environment.getProperty("login.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
    }


}
