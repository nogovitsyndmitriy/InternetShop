package com.gmail.nogovitsyndmitriy.service;

import java.util.List;

public interface GenericService<T> {

    T get(final Long id);

    T save(final T dto);

    T update(final T dto);

    void delete(final T dto);

    void deleteById(final Long id);

    List<T> getAll();
}
