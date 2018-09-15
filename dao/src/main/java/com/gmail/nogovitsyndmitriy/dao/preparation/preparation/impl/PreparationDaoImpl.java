package com.gmail.nogovitsyndmitriy.dao.preparation.preparation.impl;

import com.gmail.nogovitsyndmitriy.dao.preparation.preparation.dao.PreparationDAO;
import com.gmail.nogovitsyndmitriy.dao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public abstract class PreparationDaoImpl<T extends Serializable> implements PreparationDAO<T> {
    private Class<T> clazz;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public PreparationDaoImpl(Class<T> clazz) {
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
