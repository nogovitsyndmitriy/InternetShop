package com.gmail.nogovitsyndmitriy.dao.connection;

import java.util.ResourceBundle;

public class ConnectionManager {

    private static ConnectionManager instance;

    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";

    public static final String DATABASE_DRIVER_NAME = "database.driver.name";
    public static final String DATABASE_URL = "database.url";
    public static final String DATABASE_USERNAME = "database.username";
    public static final String DATABASE_PWD = "database.password";
    public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current_session_context_class";
    public static final String USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    public static final String CACHE_REGION_FACTORY = "hibernate.cache.region.factory_class";
//    public static final String MISSING_CACHE_STRATEGY = "hibernate.cache.ehcache.missing_cache_strategy";

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
