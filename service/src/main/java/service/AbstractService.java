package service;

import dbConnection.ConnectionManager;
import dbConnection.DbManagerException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public abstract class AbstractService {

    public static final Logger log = Logger.getLogger(String.valueOf(AbstractService.class));

    public void startTransaction() throws SQLException {
        ConnectionManager.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionManager.getConnection().commit();
    }

    public Connection getConnection(){
        return ConnectionManager.getConnection();
    }

    public void rollback(){
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DbManagerException("Rollback error");
        }
    }
}
