package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.NewsDto;

import java.util.List;

public interface NewsService extends GenericService<NewsDto> {
    List<NewsDto> newsPagination(Long page, int maxResult);

    Long quantityOfNews();
}
