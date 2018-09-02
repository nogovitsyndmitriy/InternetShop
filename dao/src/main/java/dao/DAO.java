package dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public interface DAO<T> {

    T save(T t) throws SQLException;

    T get(Serializable id) throws SQLException;

    void update(T t) throws SQLException;

    int delete(Serializable id) throws SQLException;

    List<T> getAll() throws SQLException;

    Session getCurrentSession();
}
