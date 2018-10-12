package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;

import java.util.List;

public interface GenericService<T> {

    T get(final Long id);

    T save(final T dto);

    T update(final T dto);

    void delete(final T dto);

    BusinessCardDto deleteById(final Long id);

    List<T> getAll();
}
