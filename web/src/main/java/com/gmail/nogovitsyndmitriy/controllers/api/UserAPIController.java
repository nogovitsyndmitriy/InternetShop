package com.gmail.nogovitsyndmitriy.controllers.api;

import com.gmail.nogovitsyndmitriy.service.model.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {
    private Random random = new Random();
    private Map<Integer, UserDto> users = new HashMap<>();


    @PostMapping
    public UserDto userSave(@RequestBody UserDto user) {
        return users.put(random.nextInt(), user);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return new ArrayList<>(users.values());
    }

    @GetMapping(value = "/{id}")
    private UserDto getUser(@PathVariable(name = "id") Integer id) {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John");
        users.put(1,userDto);
        return users.get(id);
    }

}
