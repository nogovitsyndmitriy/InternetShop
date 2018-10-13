package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;

import java.util.List;

public interface BusinessCardService extends GenericService<BusinessCardDto> {
    List<BusinessCardDto> getAllById(Long id);

    void deleteById(final Long id);
}
