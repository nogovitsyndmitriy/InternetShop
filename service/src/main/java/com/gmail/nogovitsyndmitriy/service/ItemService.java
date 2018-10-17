package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService extends GenericService<ItemDto> {

    List<ItemDto> findItemInRangeOfPrice(BigDecimal above, BigDecimal below);

    long count(BigDecimal above, BigDecimal below);

    List<ItemDto> itemPagination(Long page, int maxResult);

    List<ItemDto> itemPaginationManage(Long page, int maxResult);

    Long quantityOfItems();

    void uploadFromFile(MultipartFile file);

    Boolean findItemInOrders(Long itemId);
}
