package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.DiscountDao;
import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.service.DiscountService;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;
import com.gmail.nogovitsyndmitriy.service.model.DiscountDto;
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
public class DiscountServiceImpl implements DiscountService {
    private final static Logger log = LogManager.getLogger(DiscountServiceImpl.class);
    private final DTOConverter<DiscountDto, Discount> discountDtoConverter;
    private final Converter<Discount, DiscountDto> discountConverter;
    private final DTOConverter<ItemDto, Item> itemDtoConverter;
    private DiscountDao discountDao;
    private ItemDao itemDao;

    @Autowired
    public DiscountServiceImpl(@Qualifier("discountDtoConverter") DTOConverter<DiscountDto, Discount> discountDtoConverter,
                               @Qualifier("discountConverter") Converter<Discount, DiscountDto> discountConverter,
                               @Qualifier("itemDtoConverter") DTOConverter<ItemDto, Item> itemDtoConverter,
                               DiscountDao discountDao, ItemDao itemDao) {
        this.discountDtoConverter = discountDtoConverter;
        this.discountConverter = discountConverter;
        this.itemDtoConverter = itemDtoConverter;
        this.discountDao = discountDao;
        this.itemDao = itemDao;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public DiscountDto get(Long id) {
        DiscountDto discountDto = new DiscountDto();
        try {
            Discount discount = discountDao.get(id);
            discountDto = discountDtoConverter.toDTO(discount);
            log.info("Get Discount successful!");
        } catch (Exception e) {
            log.error("Failed to get Discount!", e);
        }
        return discountDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public DiscountDto save(DiscountDto discountDto) {
        try {
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.save(discount);
            discountDto = discountDtoConverter.toDTO(discount);
            log.info("Discount save successful!");
        } catch (Exception e) {
            log.error("Failed to save Discount!", e);
        }
        return discountDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public DiscountDto update(DiscountDto discountDto) {
        try {
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.update(discount);
            discountDto = discountDtoConverter.toDTO(discount);
            log.info("Discount Update Successful!");
        } catch (Exception e) {
            log.error("Failed to Update Discount!", e);
        }
        return discountDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<ItemDto> findByAmountOfDiscount(BigDecimal percent) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        try {
            List<Item> items = discountDao.findByAmountOfDiscount(percent);
            for (Item item : items) {
                ItemDto itemDto = itemDtoConverter.toDTO(item);
                itemDtoList.add(itemDto);
            }
            log.info("Find by amount discount Successful!");
        } catch (Exception e) {
            log.error("Failed to Find by amount Discount!", e);
        }
        return itemDtoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void addDiscountByItemPrice(DiscountDto discountDto, BigDecimal above, BigDecimal below) {
        try {
            List<Item> items = itemDao.findItemInRangeOfPrice(above, below);
            items.forEach(item -> discountDto.getItemDtoSet().add(itemDtoConverter.toDTO(item)));
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.update(discount);
            log.info("Add discount by item price successful!");
        } catch (Exception e) {
            log.error("Failed to add discount by item price!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void delete(DiscountDto discountDto) {
        try {
            Discount discount = discountConverter.toEntity(discountDto);
            discountDao.delete(discount);
            log.info("Discount delete successful!");
        } catch (Exception e) {
            log.error("Failed to delete discount!");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void deleteById(Long id) {
        try {
            Discount discount = discountDao.get(id);
            discountDao.delete(discount);
            log.info("Get feedback by Id successful!");
        } catch (Exception e) {
            log.error("Failed to get feedback by Id!");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public List<DiscountDto> getAll() {
        List<DiscountDto> discounts = new ArrayList<>();
        try {
            discounts = discountDtoConverter.toDtoList(discountDao.getAll());
            log.info("Get all discounts successful!");
        } catch (Exception e) {
            log.info("Get all discounts failed!");
        }
        return discounts;
    }

    @Override
    public DiscountDto findByName(String name) {
        Discount discount = discountDao.findByName(name);
        DiscountDto discountDto = discountDtoConverter.toDTO(discount);
        return discountDto;
    }
}
