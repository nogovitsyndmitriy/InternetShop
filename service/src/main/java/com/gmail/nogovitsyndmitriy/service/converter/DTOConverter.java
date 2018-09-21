package com.gmail.nogovitsyndmitriy.service.converter;

import java.util.List;

public interface DTOConverter<D, E> {
    D toDTO(E entity);

    List<D> toDtoList(List<E> list);
}

