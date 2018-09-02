package dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;
import java.io.Serializable;
import java.util.List;

public abstract class GenericAbstractDao<T extends Serializable> {

    private Class<T> clazz;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    GenericAbstractDao(Class<T> clazz) {
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
