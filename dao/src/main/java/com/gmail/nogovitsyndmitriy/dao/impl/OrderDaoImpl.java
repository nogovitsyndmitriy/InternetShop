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

    public List<Order> findOrdersByUserId(long userId) {
        String hql = "FROM Order AS O WHERE O.user.id =: userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return query.list();
    }
}
