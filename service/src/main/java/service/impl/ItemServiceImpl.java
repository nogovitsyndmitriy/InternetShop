package service.impl;

import dao.ItemDao;
import dao.impl.ItemDaoImpl;
import entities.Item;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AbstractService;
import service.ItemService;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class ItemServiceImpl extends AbstractService implements ItemService {
    private static final Logger log = LogManager.getLogger(ItemServiceImpl.class);
    private static volatile ItemService INSTANCE = null;
    private ItemDao itemDao = ItemDaoImpl.getINSTANCE();
    Item item = new Item();

    public static ItemService getINSTANCE(){
        ItemService itemService = INSTANCE;
        if(itemService == null){
            synchronized (ItemServiceImpl.class){
                itemService = INSTANCE;
                if(itemService == null){
                    INSTANCE = itemService = new ItemServiceImpl();
                }
            }
        }
        return itemService;
    }

    @Override
    public Item save(Item item) {
        try {
            startTransaction();
            item = itemDao.save(item);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return item;
    }

    @Override
    public Item get(Serializable id) {
        try {
            startTransaction();
            item = itemDao.get(id);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return item;
    }

    @Override
    public void update(Item item) {
        try {
            startTransaction();
            itemDao.update(item);
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
    }

    @Override
    public int delete(Serializable id) {
        try {
            startTransaction();
            itemDao.delete(id);
            commit();
        } catch (SQLException e) {
            log.info(e.getMessage(), e);
            rollback();
        }
        return 0;
    }

    @Override
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        try {
            startTransaction();
            list = itemDao.getAll();
            commit();
        } catch (SQLException e) {
            rollback();
            log.info(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<Item> getAllByUserId(Serializable id) {
        List<Item> list = new LinkedList<>();
        try {
            startTransaction();
            list = itemDao.getAllByUserId(id);
            commit();
        } catch (SQLException e) {
            rollback();
            log.info(e.getMessage(), e);
        }
        return list;
    }
}
