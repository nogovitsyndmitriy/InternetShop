package com.gmail.nogovitsyndmitriy.service.model;

import com.gmail.nogovitsyndmitriy.dao.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDateTime created;
    private Integer quantity;
    private Status status;
    private ItemDto itemDto;
    private UserDto userDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(created, orderDto.created) &&
                Objects.equals(quantity, orderDto.quantity) &&
                status == orderDto.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, quantity, status);
    }
}
