package service;

import java.util.List;

public interface GenericService<T> {

    T get(final long id);

    T save(final T dto);

    T update(final T dto);

    void delete(final T dto);

    void deleteById(final long id);

    List<T> getAll();
}