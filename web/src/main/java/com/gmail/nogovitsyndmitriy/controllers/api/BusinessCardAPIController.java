package com.gmail.nogovitsyndmitriy.controllers.api;

import com.gmail.nogovitsyndmitriy.service.BusinessCardService;
import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class BusinessCardAPIController {
    private final BusinessCardService cardService;

    @Autowired
    public BusinessCardAPIController(BusinessCardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/{id}")
    public List<BusinessCardDto> getCardsById(@PathVariable("id") Long id) {
        List<BusinessCardDto> cards = cardService.getAllById(id);
        return cards;
    }

    @GetMapping(value = "/card/{id}")
    public BusinessCardDto getOne(@PathVariable("id") Long id) {
        return cardService.get(id);
    }
}
