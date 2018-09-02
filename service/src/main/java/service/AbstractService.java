package service;

import dbConnection.ConnectionManagerOld;
import dbConnection.DbManagerException;
import java.sql.Connection;
import java.sql.SQLException;


public abstract class AbstractService {


    public void startTransaction() throws SQLException {
        ConnectionManagerOld.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        ConnectionManagerOld.getConnection().commit();
    }

    public Connection getConnection() {
        return ConnectionManagerOld.getConnection();
    }

    public void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new DbManagerException("Rollback error");
        }
    }
}
