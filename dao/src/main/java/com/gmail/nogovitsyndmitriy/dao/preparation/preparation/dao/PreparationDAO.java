package com.gmail.nogovitsyndmitriy.dao.preparation.preparation.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface PreparationDAO<T extends Serializable> {
    T get(final long entityId);

    void save(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);

    List<T> getAll();

    Session getCurrentSession();
}
