package com.gmail.nogovitsyndmitriy.service;

import org.junit.Test;
import com.gmail.nogovitsyndmitriy.service.impl.DiscountServiceImpl;
import com.gmail.nogovitsyndmitriy.service.impl.ItemServiceImpl;
import com.gmail.nogovitsyndmitriy.service.impl.OrderServiceImpl;
import com.gmail.nogovitsyndmitriy.service.impl.UserServiceImpl;
import com.gmail.nogovitsyndmitriy.service.model.DiscountDto;
import com.gmail.nogovitsyndmitriy.service.model.ItemDto;
import com.gmail.nogovitsyndmitriy.service.model.OrderDto;
import com.gmail.nogovitsyndmitriy.service.model.UserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class DiscountTesttt {
//
//    private UserService userService = new UserServiceImpl();
//    private ItemService itemService = new ItemServiceImpl();
//    private DiscountService discountService = new DiscountServiceImpl();
//    private OrderService orderService = new OrderServiceImpl();
//    private Random random = new Random();
//
//
//    @Test
//    public void createItems() {
////        Create 30 Items (tusk 2)
//        for (int i = 1; i <= 30; i++) {
//            ItemDto itemDto = new ItemDto();
//            itemDto.setName("item_" + i);
//            itemDto.setUniqueNumber("Unique" + String.valueOf(i + 4));
//            itemDto.setDescription("Some description_" + i);
//            itemDto.setPrice(BigDecimal.valueOf(random.nextInt(400) + 100));
//            itemService.save(itemDto);
//        }
//    }
//
//    @Test
//    public void createDiscounts() {
////        Create Discounts (tusk 3)
//        for (int i = 1; i <= 3; i++) {
//            DiscountDto discountDto = new DiscountDto();
//            discountDto.setName("Discount_" + i * 10 + "%");
//            discountDto.setPercent(BigDecimal.valueOf(i));
//            discountDto.setValid(LocalDateTime.now().plusDays(30));
//            discountService.save(discountDto);
//        }
//    }
//
//    @Test
//    public void DiscountForItems() {
////        10% Discount (tusk 3)
//        DiscountDto discountDto = discountService.get(1L);
//        discountService.addDiscountByItemPrice(discountDto, BigDecimal.valueOf(200), BigDecimal.valueOf(299));
////        20% Discount
//        DiscountDto discountDto2 = discountService.get(2L);
//        discountService.addDiscountByItemPrice(discountDto2, BigDecimal.valueOf(300), BigDecimal.valueOf(399));
////        30% Discount
//        DiscountDto discountDto3 = discountService.get(3L);
//        discountService.addDiscountByItemPrice(discountDto3, BigDecimal.valueOf(400), BigDecimal.valueOf(500));
//    }
//
//    @Test
//    public void findByAmountOfDiscount() {
////        (tusk 5)
//        List<ItemDto> itemDtoList = discountService.findByAmountOfDiscount(BigDecimal.valueOf(0));
//        itemDtoList.stream().forEach(System.out::println);
//    }
//
//    @Test
//    public void discountForUser() {
////        Create User (tusk 6)
//        UserDto userDto = new UserDto();
//        userDto.setName("Homer");
//        userDto.setSurname("Simpson");
//        userDto.setEmail("TheSimpsons@gmail.com");
//        userDto.setPassword("IloveDuff");
////        Give discount to User (tusk7)
//        int j = random.nextInt(3) + 1;
//        DiscountDto discountDto = discountService.get((long) j);
//        userDto.setDiscountDto(discountDto);
//        userService.update(userDto);
//    }
//
//    @Test
//    public void fourOrdersForUser() {
////        (tusk 8)
//        UserDto homer = userService.get(1L);
//        for (int i = 0; i <= 4; i++) {
//            OrderDto orderDto = new OrderDto();
//            List<ItemDto> itemDtoList = itemService.findItemInRangeOfPrice(BigDecimal.valueOf(250), BigDecimal.valueOf(450));
//            ItemDto itemDto = itemDtoList.get(random.nextInt(itemDtoList.size() - 1));
//            orderDto.setItemDto(itemDto);
//            orderDto.setUserDto(homer);
//            orderDto.setQuantity(Math.toIntExact(itemService.count(BigDecimal.valueOf(250), BigDecimal.valueOf(450))));
//            orderDto.setCreated(LocalDateTime.now());
//            orderDto.setStatus("NEW");
//            orderService.update(orderDto);
//        }
//    }
}
