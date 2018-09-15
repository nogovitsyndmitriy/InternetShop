package service.impl;

import com.gmail.nogovitsyndmitriy.dao.ItemDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.impl.ItemDaoImpl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import service.ItemService;
import service.converter.impl.dto.ItemDtoConverter;
import service.converter.impl.entity.ItemConverter;
import service.model.ItemDto;

import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private final static Logger log = LogManager.getLogger(ItemServiceImpl.class);
    private ItemDtoConverter itemDtoConverter = new ItemDtoConverter();
    private ItemConverter itemConverter = new ItemConverter();
    private ItemDao itemDao = new ItemDaoImpl(Item.class);
    private ItemDto itemDto = new ItemDto();
    private Item item = new Item();

    @Override
    public ItemDto get(long id) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            item = itemDao.get(id);
            itemDto = itemDtoConverter.toDTO(item);
            transaction.commit();
            log.info("Get item by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Get item by Id failed!", e);
        }
        return itemDto;
    }

    @Override
    public ItemDto save(ItemDto dto) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            item = itemConverter.toEntity(dto);
            itemDao.save(item);
            itemDto = itemDtoConverter.toDTO(item);
            transaction.commit();
            log.info("Saving item successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Saving item failed!", e);
        }
        return itemDto;
    }

    @Override
    public ItemDto update(ItemDto dto) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            item = itemConverter.toEntity(dto);
            itemDao.update(item);
            itemDto = itemDtoConverter.toDTO(item);
            transaction.commit();
            log.info("Update item successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Update item failed!", e);
        }
        return itemDto;
    }

    @Override
    public void delete(ItemDto dto) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            item = itemConverter.toEntity(dto);
            itemDao.delete(item);
            transaction.commit();
            log.info("Delete item successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete item failed!", e);
        }
    }

    @Override
    public void deleteById(long id) {
        Session session = itemDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            item = itemDao.get(id);
            itemDao.delete(item);
            transaction.commit();
            log.info("Delete item by Id successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Delete item by Id failed!", e);
        }
    }

    @Override
    public List<ItemDto> findItemInRangeOfPrice(BigDecimal above, BigDecimal below) {
        Session session = itemDao.getCurrentSession();
        List<ItemDto> itemDtoList = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            List<Item> items = itemDao.findItemInRangeOfPrice(above, below);
            for (Item item : items) {
                ItemDto itemDto = itemDtoConverter.toDTO(item);
                itemDtoList.add(itemDto);
            }
            transaction.commit();
            log.info("Find items by price in range successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Find items by price in range failed!", e);
        }
        return itemDtoList;
    }

    @Override
    public long count(BigDecimal above, BigDecimal below) {
        Session session = itemDao.getCurrentSession();
        Long number = Long.valueOf(0);
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            number = itemDao.count(above, below);
            transaction.commit();
            log.info("Count get successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Count get failed!", e);
        }
        return number;
    }

    @Override
    public List<ItemDto> getAll() {
        return null;
    }
}
