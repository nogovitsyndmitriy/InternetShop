package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.DiscountDto;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountService extends GenericService<DiscountDto> {

    List<ItemDto> findByAmountOfDiscount(BigDecimal percent);

    void addDiscountByItemPrice(DiscountDto discountDto, BigDecimal above, BigDecimal below);

}
