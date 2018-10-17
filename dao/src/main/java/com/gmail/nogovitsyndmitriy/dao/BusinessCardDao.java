package com.gmail.nogovitsyndmitriy.dao;

import com.gmail.nogovitsyndmitriy.dao.entities.BusinessCard;

import java.util.List;

public interface BusinessCardDao extends GenericDao<BusinessCard> {

    List<BusinessCard> getAllById(Long id);
}
