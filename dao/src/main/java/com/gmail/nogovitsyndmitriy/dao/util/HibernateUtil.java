package com.gmail.nogovitsyndmitriy.dao.util;


import com.gmail.nogovitsyndmitriy.dao.entities.*;
import com.gmail.nogovitsyndmitriy.dao.properties.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.cfg.AvailableSettings.*;


@Component
public class HibernateUtil {

    private static final Logger log = LogManager.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static org.hibernate.SessionFactory sessionFactory;

    private final Properties properties;

    @Autowired
    public HibernateUtil(Properties properties) {
        this.properties = properties;
    }

    public org.hibernate.SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, properties.getName());
                settings.put(Environment.URL, properties.getUrl());
                settings.put(Environment.USER, properties.getUsername());
                settings.put(Environment.PASS, properties.getPassword());
                settings.put(HBM2DDL_AUTO, properties.getHbm2ddl());
                settings.put(CURRENT_SESSION_CONTEXT_CLASS, properties.getSessionContext());
                settings.put(USE_SECOND_LEVEL_CACHE, properties.getSecondLevelCache());
                settings.put(CACHE_REGION_FACTORY, properties.getRegionFactory());


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
