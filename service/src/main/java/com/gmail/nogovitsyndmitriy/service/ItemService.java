package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService extends GenericService<ItemDto> {

    List<ItemDto> findItemInRangeOfPrice(BigDecimal above, BigDecimal below);

    long count(BigDecimal above, BigDecimal below);
}
