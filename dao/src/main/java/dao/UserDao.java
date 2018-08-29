package dao;

import entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends DAO<User> {
    User getUserByAccount(String login) throws SQLException;
    List<User> getAll() throws SQLException;
}
