package dao.impl;

import dbConnection.ConnectionManagerOld;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao {

    protected PreparedStatement preparedStatement(String query) throws SQLException {
        return ConnectionManagerOld.getConnection().prepareStatement(query);
    }

    protected PreparedStatement preparedStatement(String query, int flag) throws SQLException {
        return ConnectionManagerOld.getConnection().prepareStatement(query, flag);
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getEntity(Serializable id, PreparedStatement preparedStatement, String query) throws SQLException {
        preparedStatement = preparedStatement(query);
        preparedStatement.setLong(1,(long) id);
        preparedStatement.executeQuery();
        ResultSet rs = preparedStatement.getResultSet();
    }
}
