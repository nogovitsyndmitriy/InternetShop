package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.ItemDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.ItemConverter;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final static Logger log = LogManager.getLogger(ItemServiceImpl.class);
    private final ItemDtoConverter itemDtoConverter;
    private final ItemConverter itemConverter;
    private final ItemDao itemDao;
    private ItemDto itemDto = new ItemDto();
    private Item item = new Item();

    @Autowired
    public ItemServiceImpl(@Qualifier("itemDtoConverter") ItemDtoConverter itemDtoConverter, @Qualifier("itemConverter") ItemConverter itemConverter, ItemDao itemDao) {
        this.itemDtoConverter = itemDtoConverter;
        this.itemConverter = itemConverter;
        this.itemDao = itemDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ItemDto get(long id) {
        try {
            item = itemDao.get(id);
            itemDto = itemDtoConverter.toDTO(item);
            log.info("Get item by Id successful!");
        } catch (Exception e) {
            log.error("Get item by Id failed!", e);
        }
        return itemDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ItemDto save(ItemDto dto) {
        try {
            item = itemConverter.toEntity(dto);
            itemDao.save(item);
            itemDto = itemDtoConverter.toDTO(item);
            log.info("Saving item successful!");
        } catch (Exception e) {
            log.error("Saving item failed!", e);
        }
        return itemDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ItemDto update(ItemDto dto) {
        try {
            item = itemConverter.toEntity(dto);
            itemDao.update(item);
            itemDto = itemDtoConverter.toDTO(item);
            log.info("Update item successful!");
        } catch (Exception e) {
            log.error("Update item failed!", e);
        }
        return itemDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(ItemDto dto) {
        try {
            item = itemConverter.toEntity(dto);
            itemDao.delete(item);
            log.info("Delete item successful!");
        } catch (Exception e) {
            log.error("Delete item failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(long id) {
        try {
            item = itemDao.get(id);
            itemDao.delete(item);
            log.info("Delete item by Id successful!");
        } catch (Exception e) {
            log.error("Delete item by Id failed!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<ItemDto> findItemInRangeOfPrice(BigDecimal above, BigDecimal below) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        try {
            List<Item> items = itemDao.findItemInRangeOfPrice(above, below);
            for (Item item : items) {
                ItemDto itemDto = itemDtoConverter.toDTO(item);
                itemDtoList.add(itemDto);
            }
            log.info("Find items by price in range successful!");
        } catch (Exception e) {
            log.error("Find items by price in range failed!", e);
        }
        return itemDtoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public long count(BigDecimal above, BigDecimal below) {
        Long number = Long.valueOf(0);
        try {
            number = itemDao.count(above, below);
            log.info("Count get successful!");
        } catch (Exception e) {
            log.error("Count get failed!", e);
        }
        return number;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<ItemDto> getAll() {
        List<ItemDto> items = new ArrayList<>();
        try {
            items = itemDtoConverter.toDtoList(itemDao.getAll());
            log.info("Get all items successful!");
        } catch (Exception e) {
            log.info("Get all items failed!");
        }
        return items;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<ItemDto> itemPagination(long page, int maxResult) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<Item> items;
        try{
            items = itemDao.itemPagination(page, maxResult);
            for (Item item : items){
                itemDtoList.add(itemDtoConverter.toDTO(item));
            }
            log.info("Successful getting items pagination!");
        } catch (Exception e){
            log.error("Items pagination failed!", e);
        }
        return itemDtoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public long quantityOfItems() {
        long quantity = 0;
        try {
            quantity = itemDao.quantityOfItems();
            log.info("Quantity of items getting successful!");
        } catch (Exception e) {
            log.error("Failed to get items quantity!", e);
        }
        return quantity;
    }
}
