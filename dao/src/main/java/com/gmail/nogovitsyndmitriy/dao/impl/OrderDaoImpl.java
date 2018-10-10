package com.gmail.nogovitsyndmitriy.dao.impl;

import com.gmail.nogovitsyndmitriy.dao.OrderDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Order;
import net.sf.ehcache.search.expression.Or;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

    private final static Logger log = LogManager.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl() {
        super(Order.class);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        String hql = "FROM Order AS O WHERE O.user.id =: userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }

    @Override
    public List<Order> ordersPangination(Long page, int maxResult) {
        String hql = "FROM Order AS O ORDER BY O.created DESC";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((maxResult * page) - maxResult);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

    @Override
    public Long quantityOfOrders() {
        String hql = "SELECT COUNT (*) FROM Order AS O";
        Query query = getCurrentSession().createQuery(hql);
        return (long) query.uniqueResult();
    }
    @Override
    public List<Order> ordersPanginationById(Long page, int maxResult, Long id) {
        String hql = "FROM Order AS O WHERE O.user.id=:id";
        Query query = getCurrentSession().createQuery(hql);
        int startPosition = (int) ((maxResult * page) - maxResult);
        query.setParameter("id", id);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.list();
    }

}
