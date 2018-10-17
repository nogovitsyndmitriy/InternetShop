package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.News;

import java.util.List;

public interface NewsDao extends GenericDao<News> {
    List<News> newsPagination(Long page, int maxResult);

    Long quantityOfNews();
}
