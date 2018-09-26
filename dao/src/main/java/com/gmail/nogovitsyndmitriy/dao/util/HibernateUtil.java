package com.gmail.nogovitsyndmitriy.dao.util;


import com.gmail.nogovitsyndmitriy.dao.entities.*;
import com.gmail.nogovitsyndmitriy.dao.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;


@Component
public class HibernateUtil {

    private static final Logger log = LogManager.getLogger(HibernateUtil.class);

    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;

    private final DatabaseProperties databaseProperties;

    @Autowired
    public HibernateUtil(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, databaseProperties.getName());
                settings.put(Environment.URL, databaseProperties.getUrl());
                settings.put(Environment.USER, databaseProperties.getUsername());
                settings.put(Environment.PASS, databaseProperties.getPassword());
                settings.put(Environment.HBM2DDL_AUTO, databaseProperties.getHbm2ddl());
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, databaseProperties.getSessionContext());
                settings.put(Environment.USE_SECOND_LEVEL_CACHE, databaseProperties.getSecondLevelCache());
                settings.put(Environment.CACHE_REGION_FACTORY, databaseProperties.getRegionFactory());


                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();

                log.info("Hibernate Registry builder created!");

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(News.class);
                sources.addAnnotatedClass(Comment.class);
                sources.addAnnotatedClass(User.class);
                sources.addAnnotatedClass(Profile.class);
                sources.addAnnotatedClass(Audit.class);
                sources.addAnnotatedClass(Order.class);
                sources.addAnnotatedClass(Item.class);
                sources.addAnnotatedClass(Role.class);
                sources.addAnnotatedClass(Permission.class);
                sources.addAnnotatedClass(Discount.class);
                sources.addAnnotatedClass(Feedback.class);


                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                log.info("SessionFactory created!");
            } catch (Exception e) {
                log.error("SessionFactory creation error");
                log.error(e.getMessage(), e);
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}
