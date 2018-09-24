package com.gmail.nogovitsyndmitriy.dao.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Properties {
    @Autowired
    private Environment environment;

    private String name;
    private String url;
    private String username;
    private String password;
    private String secondLevelCache;
    private String regionFactory;
    private String sessionContext;
    private String hbm2ddl;

    public void initialize() {
        this.name = environment.getProperty("database.driver.name");
        this.url = environment.getProperty("database.url");
        this.username = environment.getProperty("database.username");
        this.password = environment.getProperty("database.password");
        this.secondLevelCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.regionFactory = environment.getProperty("hibernate.cache.region.factory_class");
        this.sessionContext = environment.getProperty("hibernate.current_session_context_class");
        this.hbm2ddl = environment.getProperty("hibernate.hbm2ddl.auto");
    }
}
