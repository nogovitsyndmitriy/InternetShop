package util;

import dbConnection.ConnectionManager;
import entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

import static dbConnection.ConnectionManager.*;

public class HibernateUtil {

    private static final Logger log = LogManager.getLogger(HibernateUtil.class);

    private static StandardServiceRegistry registry;
    private static org.hibernate.SessionFactory sessionFactory;

    public static org.hibernate.SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, ConnectionManager.getInstance().getProperty(DATABASE_DRIVER_NAME));
                settings.put(Environment.URL, ConnectionManager.getInstance().getProperty(DATABASE_URL));
                settings.put(Environment.USER, ConnectionManager.getInstance().getProperty(DATABASE_USERNAME));
                settings.put(Environment.PASS, ConnectionManager.getInstance().getProperty(DATABASE_PWD));
                settings.put(Environment.HBM2DDL_AUTO, ConnectionManager.getInstance().getProperty(HBM2DDL_AUTO));
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, ConnectionManager.getInstance().getProperty(CURRENT_SESSION_CONTEXT_CLASS));

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();

                log.info("Hibernate Registry builder created!");

                MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(GenericNews.class).
                        addAnnotatedClass(GenericComment.class).addAnnotatedClass(GenericUser.class).addAnnotatedClass(GenericProfile.class)
                        .addAnnotatedClass(GenericAudit.class).addAnnotatedClass(GenericOrder.class)
                        .addAnnotatedClass(GenericItem.class).addAnnotatedClass(GenericRole.class).addAnnotatedClass(GenericRolePermission.class)
                        .addAnnotatedClass(GenericPermission.class);


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
