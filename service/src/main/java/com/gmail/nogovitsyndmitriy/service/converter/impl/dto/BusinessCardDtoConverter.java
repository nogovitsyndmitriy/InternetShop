package com.gmail.nogovitsyndmitriy.service.converter.impl.dto;

import com.gmail.nogovitsyndmitriy.dao.entities.BusinessCard;
import com.gmail.nogovitsyndmitriy.service.converter.DTOConverter;
import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("businessCardDtoConverter")
public class BusinessCardDtoConverter implements DTOConverter<BusinessCardDto, BusinessCard> {
    @Override
    public BusinessCardDto toDTO(BusinessCard entity) {
        if (entity == null) {
            return null;
        }
        BusinessCardDto card = new BusinessCardDto();
        card.setId(entity.getId());
        card.setTitle(entity.getTitle());
        card.setFullName(entity.getFullName());
        card.setWorkingTelephone(entity.getWorkingTelephone());
        card.setUser(entity.getUser());
        return card;
    }

    @Override
    public List<BusinessCardDto> toDtoList(List<BusinessCard> list) {
        List<BusinessCardDto> cards = new ArrayList<>();
        for (BusinessCard card : list) {
            cards.add(toDTO(card));
        }
        return cards;
    }
}
