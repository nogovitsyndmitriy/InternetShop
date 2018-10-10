package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemDao extends GenericDao<Item> {
    List<Item> findItemInRangeOfPrice(BigDecimal above, BigDecimal below);

    long count(BigDecimal above, BigDecimal below);

    List<Item> itemPagination(Long page, int maxResult);

    Long quantityOfItems();
}
