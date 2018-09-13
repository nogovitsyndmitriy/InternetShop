package service.converter;

import java.util.List;

public interface Converter<E, D> {
    E toEntity(D dto);

    List<E> toEntityList(List<D> list);
}
