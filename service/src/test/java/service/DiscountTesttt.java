package service;

import com.gmail.nogovitsyndmitriy.dao.entities.Discount;
import org.junit.Test;
import service.impl.DiscountServiceImpl;
import service.impl.ItemServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;
import service.model.DiscountDto;
import service.model.ItemDto;
import service.model.OrderDto;
import service.model.UserDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class DiscountTesttt {

    private UserService userService = new UserServiceImpl();
    private ItemService itemService = new ItemServiceImpl();
    private DiscountService discountService = new DiscountServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private DiscountDto discountDto = new DiscountDto();
    private Random random = new Random();


    @Test
    public void createItems() {
//        Create 30 Items (tusk 2)
        for (int i = 1; i < 31; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setName("item_" + i);
            itemDto.setUniqueNumber("Unique" + String.valueOf(i + 4));
            itemDto.setDescription("Some description_" + i);
            itemDto.setPrice(BigDecimal.valueOf(random.nextInt(500) + 200));
            itemService.save(itemDto);
        }
    }

    @Test
    public void createDiscounts() {
//        Create Discounts (tusk 3)
        for (int i = 0; i < 31; i++) {
            if (i == 10 || i == 20 || i == 30) {
                DiscountDto discountDto = new DiscountDto();
                discountDto.setName("Discount_" + i + "%");
                discountDto.setPercent(BigDecimal.valueOf(i));
                discountDto.setValid(LocalDateTime.now());
                discountService.save(discountDto);
            }
        }
    }

    @Test
    public void DiscountForItems() {
//        10% Discount (tusk 3)
        DiscountDto discountDto = discountService.get(1L);
        List<ItemDto> items = itemService.findItemInRangeOfPrice(BigDecimal.valueOf(200), BigDecimal.valueOf(299));
        items.forEach(item -> discountDto.getItemDtoSet().add(item));
        discountService.update(discountDto);
//        20% Discount
        DiscountDto discountDto2 = discountService.get(2L);
        List<ItemDto> items2 = itemService.findItemInRangeOfPrice(BigDecimal.valueOf(300), BigDecimal.valueOf(399));
        items2.forEach(item -> discountDto2.getItemDtoSet().add(item));
        discountService.update(discountDto2);
//        30% Discount
        DiscountDto discountDto3 = discountService.get(3L);
        List<ItemDto> items3 = itemService.findItemInRangeOfPrice(BigDecimal.valueOf(400), BigDecimal.valueOf(500));
        items3.forEach(item -> discountDto3.getItemDtoSet().add(item));
        discountService.update(discountDto3);
    }

    @Test
    public void findByAmountOfDiscount() {
//        (tusk 5)
        List<ItemDto> itemDtoList = discountService.findByAmountOfDiscount(BigDecimal.valueOf(0));
        itemDtoList.stream().forEach(System.out::println);
    }

    @Test
    public void discountForUser() {
//        Create User (tusk 6)
        UserDto userDto = new UserDto();
        userDto.setName("Homer");
        userDto.setSurname("Simpson");
        userDto.setEmail("TheSimpsons@gmail.com");
        userDto.setPassword("IloveDuff");
//        Give discount to User (tusk7)
        int j = random.nextInt(3) + 1;
        DiscountDto discountDto = discountService.get((long) j);
        userDto.setDiscountDto(discountDto);
        userService.update(userDto);
    }

    @Test
    public void fourOrdersForUser() {
//        (tusk 8)
        UserDto homer = userService.get(2L);
        for (int i = 0; i < 4; i++) {
            OrderDto orderDto = new OrderDto();
           List<ItemDto> itemDtoList = itemService.findItemInRangeOfPrice(BigDecimal.valueOf(250),BigDecimal.valueOf(450));
           ItemDto itemDto =  itemDtoList.get(random.nextInt(itemDtoList.size()-1));
           orderDto.setItemDto(itemDto);
           orderDto.setUserDto(homer);
           orderDto.setQuantity(Math.toIntExact(itemService.count(BigDecimal.valueOf(250), BigDecimal.valueOf(450))));
           orderDto.setCreated(LocalDateTime.now());
           orderService.update(orderDto);
        }
    }
}
