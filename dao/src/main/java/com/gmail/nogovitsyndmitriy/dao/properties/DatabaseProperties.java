package com.gmail.nogovitsyndmitriy.dao.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@Component
public class DatabaseProperties {
    private final Environment environment;

    private String name;
    private String url;
    private String username;
    private String password;
    private String secondLevelCache;
    private String regionFactory;
    private String sessionContext;
    private String hbm2ddl;
    private String poolMaxSize;
    private String cachePreparedStatement;
    private String cachePreparedStatementSize;
    private String cachePreparedStatementSqlLimit;
    private String useServerPreparedStatement;
    private String dataSourceClass;
    private String hibernateDialect;
    private String hibernateSqlShow;

    @Autowired
    public DatabaseProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.name = environment.getProperty("database.driver.name");
        this.url = environment.getProperty("database.url");
        this.username = environment.getProperty("database.username");
        this.password = environment.getProperty("database.password");
        this.secondLevelCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.regionFactory = environment.getProperty("hibernate.cache.region.factory_class");
        this.sessionContext = environment.getProperty("hibernate.current_session_context_class");
        this.hbm2ddl = environment.getProperty("hibernate.hbm2ddl.auto");
        this.poolMaxSize = environment.getProperty("pool.max.size");
        this.cachePreparedStatement = environment.getProperty("pool.cache.prepared.statements");
        this.cachePreparedStatementSize = environment.getProperty("pool.cache.prepared.statements.size");
        this.cachePreparedStatementSqlLimit = environment.getProperty("pool.cache.prepared.statements.sql.limit");
        this.useServerPreparedStatement = environment.getProperty("pool.use.server.prepared.statements");
        this.dataSourceClass = environment.getProperty("pool.data.source.class");
        this.hibernateDialect = environment.getProperty("hibernate.dialect");
        this.hibernateSqlShow = environment.getProperty("hibernate.sql.show");

    }
}
