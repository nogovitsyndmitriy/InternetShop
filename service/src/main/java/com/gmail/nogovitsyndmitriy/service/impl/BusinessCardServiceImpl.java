package com.gmail.nogovitsyndmitriy.service.impl;

import com.gmail.nogovitsyndmitriy.dao.BusinessCardDao;
import com.gmail.nogovitsyndmitriy.dao.UserDao;
import com.gmail.nogovitsyndmitriy.dao.entities.BusinessCard;
import com.gmail.nogovitsyndmitriy.dao.entities.User;
import com.gmail.nogovitsyndmitriy.service.BusinessCardService;
import com.gmail.nogovitsyndmitriy.service.converter.impl.dto.BusinessCardDtoConverter;
import com.gmail.nogovitsyndmitriy.service.converter.impl.entity.BusinessCardConverter;
import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;
import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessCardServiceImpl implements BusinessCardService {
    private final static Logger log = LogManager.getLogger(BusinessCardServiceImpl.class);
    private final BusinessCardConverter converter;
    private final BusinessCardDtoConverter dtoConverter;
    private final BusinessCardDao businessCardDao;
    private final UserDao userDao;


    @Autowired
    public BusinessCardServiceImpl(@Qualifier("businessCardConverter") BusinessCardConverter converter,
                                   @Qualifier("businessCardDtoConverter") BusinessCardDtoConverter dtoConverter,
                                   BusinessCardDao businessCardDao,
                                   UserDao userDao) {
        this.converter = converter;
        this.dtoConverter = dtoConverter;
        this.businessCardDao = businessCardDao;

        this.userDao = userDao;
    }


    @Override
    @Transactional(readOnly = true)
    public BusinessCardDto get(Long id) {
        BusinessCardDto cardDto = null;
        try {
            BusinessCard card = businessCardDao.get(id);
            cardDto = dtoConverter.toDTO(card);
            log.info("Get user successful!");
        } catch (Exception e) {
            log.error("Get user failed!", e);
        }
        return cardDto;
    }

    @Override
    @Transactional
    public BusinessCardDto save(BusinessCardDto businessCardDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        try {
            User user = userDao.get(userPrincipal.getId());
            BusinessCard card = converter.toEntity(businessCardDto);
            card.setUser(user);
            businessCardDao.save(card);
            businessCardDto = dtoConverter.toDTO(card);
            log.info("Saving card successful!");
        } catch (Exception e) {
            log.error("Saving card failed!", e);
        }
        return businessCardDto;
    }

    @Override
    @Transactional
    public BusinessCardDto update(BusinessCardDto businessCardDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(BusinessCardDto businessCardDto) {
        try {
            BusinessCard card = converter.toEntity(businessCardDto);
            businessCardDao.delete(card);
            log.info("Delete card successful!");
        } catch (Exception e) {
            log.error("Delete card failed!", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            BusinessCard card = businessCardDao.get(id);
            businessCardDao.delete(card);
            log.info("Delete card by Id successful!");
        } catch (Exception e) {
            log.error("Delete card by Id failed!", e);
        }
    }


    @Override
    @Transactional
    public List<BusinessCardDto> getAll() {
        List<BusinessCardDto> cards = new ArrayList<>();
        try {
            cards = dtoConverter.toDtoList(businessCardDao.getAll());
            log.info("Get all cards successful!");
        } catch (Exception e) {
            log.info("Get all cards failed!");
        }
        return cards;
    }

    @Override
    @Transactional
    public List<BusinessCardDto> getAllById(Long id) {
        List<BusinessCardDto> cards = new ArrayList<>();
        List<BusinessCard> list = businessCardDao.getAllById(id);
        for (BusinessCard card : list) {
            cards.add(dtoConverter.toDTO(card));
        }
        return cards;
    }
}
