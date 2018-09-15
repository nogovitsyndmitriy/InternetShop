package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountDao extends GenericDao<Discount> {

    List<Item> findByAmountOfDiscount(BigDecimal percent);
}
