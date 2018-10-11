package com.gmail.nogovitsyndmitriy.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Getter
public class PageProperties {

    private final Environment environment;

    private String loginPagePath;
    private String usersPagePath;
    private String errorsPagePath;
    private String itemsPagePath;
    private String registrationPagePath;
    private String updateUserPagePath;
    private String newsPage;
    private String singleNewsPagePath;
    private String ordersPagePath;
    private String createItemPagePath;
    private String rolesPagePath;
    private String manageItemsPagePath;
    private String createOrderPagePath;
    private String currentUserPagePath;
    private String passwordPagePath;

    @Autowired
    public PageProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.loginPagePath = environment.getProperty("login.page.path");
        this.usersPagePath = environment.getProperty("users.page.path");
        this.errorsPagePath = environment.getProperty("errors.page.path");
        this.itemsPagePath = environment.getProperty("items.page.path");
        this.registrationPagePath = environment.getProperty("registration.page.path");
        this.updateUserPagePath = environment.getProperty("update.user.page.path");
        this.newsPage = environment.getProperty("news.page.path");
        this.singleNewsPagePath = environment.getProperty("single.news.page.path");
        this.ordersPagePath = environment.getProperty("orders.page.path");
        this.createItemPagePath = environment.getProperty("create.item.page.path");
        this.rolesPagePath = environment.getProperty("roles.page.path");
        this.manageItemsPagePath = environment.getProperty("manage.items.page.path");
        this.createOrderPagePath = environment.getProperty("create.order.page.path");
        this.currentUserPagePath = environment.getProperty("current.user.page.path");
        this.passwordPagePath = environment.getProperty("password.page.path");
    }


}
