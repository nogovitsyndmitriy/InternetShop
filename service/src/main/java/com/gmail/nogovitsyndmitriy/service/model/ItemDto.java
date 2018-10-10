package com.gmail.nogovitsyndmitriy.service.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private String uniqueNumber;
    private BigDecimal price;
    private Boolean deleted;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDto)) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(id, itemDto.id) &&
                Objects.equals(name, itemDto.name) &&
                Objects.equals(description, itemDto.description) &&
                Objects.equals(uniqueNumber, itemDto.uniqueNumber) &&
                Objects.equals(price, itemDto.price) &&
                Objects.equals(deleted, itemDto.deleted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, uniqueNumber, price, deleted);
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                ", price=" + price +
                '}';
    }
}
