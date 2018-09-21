package com.gmail.nogovitsyndmitriy.dao.impl;


import com.gmail.nogovitsyndmitriy.dao.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.gmail.nogovitsyndmitriy.dao.util.HibernateUtil;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
public abstract class GenericDaoImpl<T extends Serializable> implements GenericDao<T> {

    private Class<T> clazz;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T get(long id) {
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

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
