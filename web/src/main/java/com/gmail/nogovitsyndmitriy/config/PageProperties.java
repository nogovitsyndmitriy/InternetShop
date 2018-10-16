package com.gmail.nogovitsyndmitriy.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PageProperties {


    @Value("${login.page.path}")
    private String loginPagePath;
    @Value("${users.page.path}")
    private String usersPagePath;
    @Value("${errors.page.path}")
    private String errorsPagePath;
    @Value("${items.page.path}")
    private String itemsPagePath;
    @Value("${registration.page.path}")
    private String registrationPagePath;
    @Value("${update.user.page.path}")
    private String updateUserPagePath;
    @Value("${news.page.path}")
    private String newsPage;
    @Value("${single.news.page.path}")
    private String singleNewsPagePath;
    @Value("${orders.page.path}")
    private String ordersPagePath;
    @Value("${create.item.page.path}")
    private String createItemPagePath;
    @Value("${roles.page.path}")
    private String rolesPagePath;
    @Value("${manage.items.page.path}")
    private String manageItemsPagePath;
    @Value("${create.order.page.path}")
    private String createOrderPagePath;
    @Value("${current.user.page.path}")
    private String currentUserPagePath;
    @Value("${password.page.path}")
    private String passwordPagePath;
    @Value("${business.card.page.path}")
    private String businessCardPagePath;
    @Value("${quantity.on.page}")
    private String quantityOnPage;
    @Value("${users.disable.page.path}")
    private String usersDisablePagePath;
    @Value("${create.news.page.path}")
    private String createNewsPagePath;
    @Value("${update.news.page.path}")
    private String updateNewsPagePath;
    @Value("${comments.page.path}")
    private String commentsPagePath;


}
