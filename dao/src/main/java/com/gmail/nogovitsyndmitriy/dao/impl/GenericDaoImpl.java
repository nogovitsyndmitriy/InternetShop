package com.gmail.nogovitsyndmitriy.dao.impl;


import com.gmail.nogovitsyndmitriy.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T get(Long id) {
        return getCurrentSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    public void save(T entity) {
        getCurrentSession().persist(entity);
    }

    public void update(T entity) {
        getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(Long entityId) {
        T entity = get(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
