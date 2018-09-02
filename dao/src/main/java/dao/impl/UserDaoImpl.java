package dao.impl;

import dao.UserDao;
import entities.User;
import org.hibernate.Session;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private static volatile UserDao INSTANCE = null;

    private static final String getUserByAccountLoginQuery = "SELECT * FROM e_shop.users WHERE Login = ?";
    private static final String saveUserQuery = "INSERT INTO e_shop.users (Login, Password, Name, Last_Name, Age, E_Mail) VALUES (?,?,?,?,?,?)";
    private static final String getUserQuery = "SELECT * FROM e_shop.users WHERE User_Id = ?";
    private static final String updateUserQuery = "UPDATE e_shop.users SET Login = ?, Name = ?, Last_Name = ?, Age = ?, E_Mail = ? WHERE User_Id = ?";
    private static final String deleteUserQuery = "DELETE FROM e_shop.users WHERE User_Id = ?";
    private static final String getAllUsersQuery = "SELECT * FROM e_shop.users";

    PreparedStatement psGetUserByAccount;
    PreparedStatement psSave;
    PreparedStatement psGet;
    PreparedStatement psUpdate;
    PreparedStatement psDelete;
    PreparedStatement psGetAll;

    public static UserDao getINSTANCE() {
        UserDao userDao = INSTANCE;
        if (userDao == null) {
            synchronized (UserDaoImpl.class) {
                userDao = INSTANCE;
                if (userDao == null) {
                    INSTANCE = userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    @Override
    public User getUserByAccount(String login) throws SQLException {
        psGetUserByAccount = preparedStatement(getUserByAccountLoginQuery);
        psGetUserByAccount.setString(1, login);
        psGetUserByAccount.executeQuery();
        ResultSet rs = psGetUserByAccount.getResultSet();
        while (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return populateEntity(rs);
    }

    @Override
    public User save(User user) throws SQLException {
        psSave = preparedStatement(saveUserQuery, Statement.RETURN_GENERATED_KEYS);
        psSave.setString(1, user.getLogin());
        psSave.setString(2, user.getPassword());
        psSave.setString(3, user.getName());
        psSave.setString(4, user.getLastName());
        psSave.setString(5, user.getAge());
        psSave.setString(6, user.getEmail());
        psSave.executeUpdate();
        ResultSet rs = psSave.getGeneratedKeys();
        while (rs.next()) {
            user.setId(rs.getLong(1));
        }
        close(rs);
        return user;
    }

    @Override
    public User get(Serializable id) throws SQLException {
        psGet = preparedStatement(getUserQuery);
        psGet.setLong(1, (long) id);
        psGet.executeQuery();
        ResultSet rs = psGet.getResultSet();
        while (rs.next()) {
            return populateEntity(rs);
        }
        close(rs);
        return null;
    }

    @Override
    public void update(User user) throws SQLException {
        psUpdate = preparedStatement(updateUserQuery);
        psUpdate.setString(1, user.getLogin());
        psUpdate.setString(2, user.getPassword());
        psUpdate.setString(3, user.getName());
        psUpdate.setString(4, user.getLastName());
        psUpdate.setString(5, user.getAge());
        psUpdate.setString(6, user.getEmail());
        psUpdate.executeUpdate();
    }

    @Override
    public int delete(Serializable id) throws SQLException {
        psDelete = preparedStatement(deleteUserQuery);
        psDelete.setLong(1, (long) id);
        return psDelete.executeUpdate();
    }

    private User populateEntity(ResultSet rs) throws SQLException {
        User entity = new User();
        entity.setId(rs.getLong(1));
        entity.setLogin(rs.getString(2));
        entity.setPassword(rs.getString(3));
        entity.setRole(rs.getString(4));
        entity.setName(rs.getString(5));
        entity.setLastName(rs.getString(6));
        entity.setAge(rs.getString(7));
        entity.setEmail(rs.getString(8));
        return entity;
    }

    @Override
    public List<User> getAll() throws SQLException {
        psGetAll = preparedStatement(getAllUsersQuery);
        List<User> list = new LinkedList<>();
        psGetAll.executeQuery();
        ResultSet rs = psGetAll.getResultSet();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getLong(1));
            user.setLogin(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setRole(rs.getString(4));
            user.setName(rs.getString(5));
            user.setLastName(rs.getString(6));
            user.setAge(rs.getString(7));
            user.setEmail(rs.getString(8));
            list.add(user);
        }
        close(rs);
        return list;
    }

    @Override
    public Session getCurrentSession() {
        return null;
    }
}
