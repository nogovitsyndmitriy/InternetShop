package com.gmail.nogovitsyndmitriy.controllers.api;

import com.gmail.nogovitsyndmitriy.service.BusinessCardService;
import com.gmail.nogovitsyndmitriy.service.model.BusinessCardDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {
    private Random random = new Random();
    private Map<Integer, UserDto> users = new HashMap<>();
    private BusinessCardService cardService;

    @Autowired
    public UserAPIController(BusinessCardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping
    public UserDto userSave(@RequestBody UserDto user) {
        return users.put(random.nextInt(), user);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return new ArrayList<>(users.values());
    }


    @GetMapping(value = "/cards")
    public List<BusinessCardDto> getCards() {
        return new ArrayList<>();
    }

    @DeleteMapping(value = "/{id}")
    public BusinessCardDto deleteCard(@PathVariable(name = "id") Long id) {
        return cardService.deleteById(id);
    }

    @GetMapping(value = "/cards/{id}")
    public BusinessCardDto getCard(@PathVariable(name = "id") Long id) {
        return cardService.get(id);
    }
}
