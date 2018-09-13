package service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long id;
    private LocalDateTime created;
    private int quantity;
    private ItemDto itemDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        OrderDto orderDto = (OrderDto) o;
        return id == orderDto.id &&
                quantity == orderDto.quantity &&
                Objects.equals(created, orderDto.created);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, quantity);
    }
}
