package com.gmail.nogovitsyndmitriy.service.converter.impl.entity;

import com.gmail.nogovitsyndmitriy.dao.entities.BusinessCard;
import com.gmail.nogovitsyndmitriy.service.converter.Converter;
import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("businessCardConverter")
public class BusinessCardConverter implements Converter<BusinessCard, BusinessCardDto> {
    @Override
    public BusinessCard toEntity(BusinessCardDto dto) {
        if (dto == null) {
            return null;
        }
        BusinessCard businessCard = new BusinessCard();
        businessCard.setId(dto.getId());
        businessCard.setTitle(dto.getTitle());
        businessCard.setFullName(dto.getFullName());
        businessCard.setWorkingTelephone(dto.getWorkingTelephone());
        businessCard.setUser(dto.getUser());
        return businessCard;
    }

    @Override
    public List<BusinessCard> toEntityList(List<BusinessCardDto> list) {
        List<BusinessCard> cards = new ArrayList<>();
        for (BusinessCardDto businessCardDto : list) {
            cards.add(toEntity(businessCardDto));
        }
        return cards;
    }
}
