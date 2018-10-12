package com.gmail.nogovitsyndmitriy.dao.properties;

import com.gmail.nogovitsyndmitriy.dao.entities.*;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    private final DatabaseProperties databaseProperties;

    @Autowired
    public DatabaseConfig(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource source = new HikariDataSource();
        source.setPoolName("R2D2 Connection Pool");
        source.setMaximumPoolSize(Integer.parseInt(databaseProperties.getPoolMaxSize()));
        source.setDataSourceClassName(databaseProperties.getDataSourceClass());
        source.addDataSourceProperty("url", databaseProperties.getUrl());
        source.addDataSourceProperty("user", databaseProperties.getUsername());
        source.addDataSourceProperty("password", databaseProperties.getPassword());
        source.addDataSourceProperty("cachePrepStmts", databaseProperties.getCachePreparedStatement());
        source.addDataSourceProperty("prepStmtCacheSize", databaseProperties.getCachePreparedStatementSize());
        source.addDataSourceProperty("prepStmtCacheSqlLimit", databaseProperties.getCachePreparedStatementSqlLimit());
        source.addDataSourceProperty("useServerPrepStmts", databaseProperties.getUseServerPreparedStatement());
        return source;
    }

    @Bean
    @DependsOn("springLiquibase")
    public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        Properties properties = new Properties();
        properties.put(DIALECT, databaseProperties.getHibernateDialect());
        properties.put(SHOW_SQL, databaseProperties.getHibernateSqlShow());
        properties.put(HBM2DDL_AUTO, databaseProperties.getHbm2ddl());
        properties.put(USE_SECOND_LEVEL_CACHE, databaseProperties.getSecondLevelCache());
        properties.put(CACHE_REGION_FACTORY, databaseProperties.getRegionFactory());
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(
                News.class,
                Comment.class,
                User.class,
                Profile.class,
                Audit.class,
                Order.class,
                Item.class,
                Role.class,
                Permission.class,
                Discount.class,
                Feedback.class,
                BusinessCard.class
        );
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setDropFirst(Boolean.TRUE);
        liquibase.setChangeLog("classpath:migration/db-changelog.xml");
        return liquibase;
    }

}
