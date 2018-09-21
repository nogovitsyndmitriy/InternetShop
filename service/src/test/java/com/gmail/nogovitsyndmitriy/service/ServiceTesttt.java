package com.gmail.nogovitsyndmitriy.service;

import com.gmail.nogovitsyndmitriy.dao.entities.User;
import org.junit.Test;
import com.gmail.nogovitsyndmitriy.service.impl.*;
import com.gmail.nogovitsyndmitriy.service.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ServiceTesttt {
//    private UserService userService = new UserServiceImpl();
//    private UserDto userDto = new UserDto();
//    private ProfileDto profileDto = new ProfileDto();
//    private RoleService roleService = new RoleServiceImpl();
//    private ItemService itemService = new ItemServiceImpl();
//    private CommentService commentService = new CommentServiceImpl();
//    private NewsService newsService = new NewsServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
//
//
//    @Test
//    public void fullTest() {
//        /*Create User*/
//        userDto.setName("John");
//        userDto.setSurname("Dow");
//        userDto.setEmail("jd@mail.ru");
//        userDto.setPassword("123qwe");
//        /*Create Profile*/
//        profileDto.setAddress("Minsk");
//        profileDto.setTelephone("34567890");
//        /*Set Profile to User & User save*/
//        userDto.setProfileDto(profileDto);
//        userService.save(userDto);
//        /*Create Audit*/
//        AuditDto auditDto = new AuditDto();
//        auditDto.setEventType("Some user action...");
//        auditDto.setCreated(LocalDateTime.now());
//        UserDto userAudit = userService.get(1L);
//        userAudit.getAuditDtoSet().add(auditDto);
//        userService.update(userAudit);
//        /*Roles & Permissions*/
//        RoleDto roleDto = new RoleDto();
//        PermissionDto permissionDto = new PermissionDto();
//        PermissionDto permissionDto2 = new PermissionDto();
//        permissionDto.setName("can create");
//        permissionDto2.setName("can delete");
//
//        roleDto.setName("ADMIN");
//        roleService.save(roleDto);
//
//        RoleDto roleForUser = roleService.get(1L);
//        roleForUser.getPermissionDtoSet().add(permissionDto);
//        roleForUser.getPermissionDtoSet().add(permissionDto2);
//
//        roleService.update(roleForUser);
//        /*Role to User*/
//        RoleDto roleAdmin = roleService.get(1L);
//        UserDto newUser = userService.get(1L);
//        newUser.setRoleDto(roleAdmin);
//        userService.update(newUser);
//
//        /*News And Comments*/
//        NewsDto news1 = new NewsDto();
//        news1.setTitle("Hot News!");
//        news1.setContent("It's ALIVE!");
//        news1.setCreated(LocalDateTime.now());
//        newsService.save(news1);
//        UserDto userCommentator = userService.get(1L);
//
//        CommentDto commentDto = new CommentDto();
//        commentDto.setContent("By the way, about this article...");
//        commentDto.setCreated(LocalDateTime.now());
//        NewsDto news2 = newsService.get(1L);
//        commentDto.setUserDto(userCommentator);
//        commentDto.setNewsDto(news2);
//        commentService.save(commentDto);
//        /*Orders and Items for User*/
//        ItemDto newItem = new ItemDto();
//        newItem.setName("Duff");
//        newItem.setDescription("by the Simpson's...");
//        newItem.setUniqueNumber("c3p0&R2D2");
//        newItem.setPrice(BigDecimal.valueOf(3.55));
//        itemService.save(newItem);
//        ItemDto newItem2 = itemService.get(1L);
//        UserDto userAgain = userService.get(1L);
//        OrderDto orderDto = new OrderDto();
//        orderDto.setQuantity(6);
//        orderDto.setCreated(LocalDateTime.now());
//        orderDto.setItemDto(newItem2);
//        orderDto.setUserDto(userAgain);
//        orderService.save(orderDto);
//        NewsDto newsToUser = newsService.get(1L);
//        userAgain.getNewsDtoSet().add(newsToUser);
//        userService.update(userAgain);
//
//    }
}
