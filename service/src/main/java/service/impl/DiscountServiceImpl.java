package service.impl;

import com.gmail.nogovitsyndmitriy.dao.DiscountDao;
import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import com.gmail.nogovitsyndmitriy.dao.entities.Item;
import com.gmail.nogovitsyndmitriy.dao.impl.DiscountDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.DiscountService;
import service.converter.impl.dto.DiscountDtoConverter;
import service.converter.impl.dto.ItemDtoConverter;
import service.converter.impl.entity.DiscountConverter;
import service.model.DiscountDto;
import service.model.ItemDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private final static Logger log = LogManager.getLogger(DiscountServiceImpl.class);
    private DiscountDtoConverter discountDtoConverter = new DiscountDtoConverter();
    private DiscountConverter discountConverter = new DiscountConverter();
    private DiscountDto discountDto = new DiscountDto();
    private DiscountDao discountDao = new DiscountDaoImpl(Discount.class);
    private Discount discount = new Discount();
    private ItemDtoConverter itemDtoConverter = new ItemDtoConverter();


    @Override
    public DiscountDto get(long id) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            discount = discountDao.get(id);
            discountDto = discountDtoConverter.toDTO(discount);
            transaction.commit();
            log.info("Get Discount successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Failed to get Discount!", e);
        }
        return discountDto;
    }

    @Override
    public DiscountDto save(DiscountDto discountDto) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            discount = discountConverter.toEntity(discountDto);
            discountDao.save(discount);
            discountDto = discountDtoConverter.toDTO(discount);
            transaction.commit();
            log.info("Discount save successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Failed to save Discount!", e);
        }
        return discountDto;
    }

    @Override
    public DiscountDto update(DiscountDto discountDto) {
        Session session = discountDao.getCurrentSession();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            discount = discountConverter.toEntity(discountDto);
            discountDao.update(discount);
            discountDto = discountDtoConverter.toDTO(discount);
            transaction.commit();
            log.info("Discount Update Successful!");
        } catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            log.error("Failed to Update Discount!", e);
        }
        return discountDto;
    }

    @Override
    public List<ItemDto> findByAmountOfDiscount(BigDecimal percent) {
        Session session = discountDao.getCurrentSession();
        List<ItemDto> itemDtoList = new ArrayList<>();
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            List<Item> items = discountDao.findByAmountOfDiscount(percent);
            for(Item item : items){
                ItemDto itemDto = itemDtoConverter.toDTO(item);
                itemDtoList.add(itemDto);
            }
                transaction.commit();
                log.info("Find by amount discount Successful!");
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                log.error("Failed to Find by amount Discount!", e);
            }
            return itemDtoList;
    }

    @Override
    public void delete(DiscountDto dto) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public List<DiscountDto> getAll() {
        return null;
    }
}
