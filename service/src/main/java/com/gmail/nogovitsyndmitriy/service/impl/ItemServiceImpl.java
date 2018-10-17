package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.service.ItemService;
import com.gmail.nogovitsyndmitriy.service.OrderService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.ItemDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.ItemConverter;
import com.gmail.nogovitsyndmitriy.service.exceptions.ServiceException;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import com.gmail.nogovitsyndmitriy.service.model.UploadedFileDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final static Logger log = LogManager.getLogger(ItemServiceImpl.class);
    private final ItemDtoConverter itemDtoConverter;
    private final ItemConverter itemConverter;
    private final ItemDao itemDao;
    private final OrderService orderService;


    @Autowired
    public ItemServiceImpl(@Qualifier("itemDtoConverter") ItemDtoConverter itemDtoConverter,
                           @Qualifier("itemConverter") ItemConverter itemConverter,
                           ItemDao itemDao,
                           OrderService orderService
    ) {
        this.itemDtoConverter = itemDtoConverter;
        this.itemConverter = itemConverter;
        this.itemDao = itemDao;
        this.orderService = orderService;
    }

    @Override
    @Transactional(readOnly = true)
    public ItemDto get(Long id) {
        ItemDto itemDto;
        Item item = itemDao.get(id);
        if (item != null) {
            itemDto = itemDtoConverter.toDTO(item);
            log.info("Get item by Id successful!");
        } else {
            throw new EntityNotFoundException("Entity with id: " + id + " not found!");
        }
        return itemDto;
    }

    @Override
    @Transactional
    public ItemDto save(ItemDto itemDto) {
        try {
            Item item = itemConverter.toEntity(itemDto);
            item.setDeleted(false);
            itemDao.save(item);
            itemDto = itemDtoConverter.toDTO(item);
            log.info("Saving item successful!");
        } catch (Exception e) {
            log.error("Saving item failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return itemDto;
    }

    @Override
    @Transactional
    public ItemDto update(ItemDto itemDto) {
        try {
            Item item = itemConverter.toEntity(itemDto);
            itemDao.update(item);
            itemDto = itemDtoConverter.toDTO(item);
            log.info("Update item successful!");
        } catch (Exception e) {
            log.error("Update item failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return itemDto;
    }

    @Override
    @Transactional
    public void delete(ItemDto itemDto) {
        Item item = itemConverter.toEntity(itemDto);
        if (item != null) {
            itemDao.delete(item);
            log.info("Delete item successful!");
        } else {
            throw new ServiceException("Service Exception!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Item item = itemDao.get(id);
        if (item != null) {
            itemDao.delete(item);
            log.info("Delete item by Id successful!");
        } else {
            throw new EntityNotFoundException("Entity with id: " + id + " not found!");
        }
    }

    @Override
    @Transactional
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
            throw new ServiceException("Service Exception!");
        }
        return itemDtoList;
    }

    @Override
    @Transactional
    public long count(BigDecimal above, BigDecimal below) {
        Long number;
        try {
            number = itemDao.count(above, below);
            log.info("Count get successful!");
        } catch (Exception e) {
            log.error("Count get failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return number;
    }

    @Override
    @Transactional
    public List<ItemDto> getAll() {
        List<ItemDto> items;
        try {
            items = itemDtoConverter.toDtoList(itemDao.getAll());
            log.info("Get all items successful!");
        } catch (Exception e) {
            log.info("Get all items failed!");
            throw new ServiceException("Service Exception!");
        }
        return items;
    }

    @Override
    @Transactional
    public List<ItemDto> itemPagination(Long page, int maxResult) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<Item> items;
        try {
            items = itemDao.itemPagination(page, maxResult);
            for (Item item : items) {
                itemDtoList.add(itemDtoConverter.toDTO(item));
            }
            log.info("Successful getting items pagination!");
        } catch (Exception e) {
            log.error("Items pagination failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return itemDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public Long quantityOfItems() {
        Long quantity;
        try {
            quantity = itemDao.quantityOfItems();
            log.info("Quantity of items getting successful!");
        } catch (Exception e) {
            log.error("Failed to get items quantity!", e);
            throw new ServiceException("Service Exception!");
        }
        return quantity;
    }

    @Override
    @Transactional
    public void uploadFromFile(MultipartFile file) {
        try {
            JAXBContext context = JAXBContext.newInstance(UploadedFileDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File tmpFile = new File("tmp.xml");
            file.transferTo(tmpFile);
            UploadedFileDto uploadedFileDto = (UploadedFileDto) unmarshaller.unmarshal(new FileReader(tmpFile));
            List<ItemDto> itemsDto = uploadedFileDto.getItems();
            for (ItemDto itemDto : itemsDto) {
                Item item = itemConverter.toEntity(itemDto);
                itemDao.save(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<ItemDto> itemPaginationManage(Long page, int maxResult) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        List<Item> items;
        try {
            items = itemDao.itemPaginationManage(page, maxResult);
            for (Item item : items) {
                itemDtoList.add(itemDtoConverter.toDTO(item));
            }
            log.info("Successful getting items pagination!");
        } catch (Exception e) {
            log.error("Items pagination failed!", e);
            throw new ServiceException("Service Exception!");
        }
        return itemDtoList;
    }

    @Override
    @Transactional
    public Boolean findItemInOrders(Long itemId) {
        Item item = itemDao.get(itemId);
        int count = 0;
        List<OrderDto> orders = orderService.getAll();
        for (OrderDto order : orders) {
            if (order.getItemDto().getId().equals(itemId)) {
                count++;
            }
        }
        if (count > 0) {
            item.setDeleted(true);
            itemDao.update(item);
            return true;
        } else {
            itemDao.delete(item);
            return false;
        }
    }
}
