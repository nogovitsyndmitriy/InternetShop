package service.model;

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

    private long id;
    private LocalDateTime created;
    private int quantity;
    private String status;
    private ItemDto itemDto;
    private UserDto userDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        OrderDto orderDto = (OrderDto) o;
        return id == orderDto.id &&
                quantity == orderDto.quantity &&
                Objects.equals(created, orderDto.created) &&
                Objects.equals(status, orderDto.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, quantity, status);
    }
}
