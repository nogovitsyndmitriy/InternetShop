package com.gmail.nogovitsyndmitriy.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DiscountDto {

    private Long id;
    private String name;
    private BigDecimal percent;
    private LocalDateTime valid;
    private Set<ItemDto> itemDtoSet = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountDto)) return false;
        DiscountDto that = (DiscountDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(percent, that.percent) &&
                Objects.equals(valid, that.valid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, percent, valid);
    }

    @Override
    public String toString() {
        return "DiscountDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percent=" + percent +
                ", valid=" + valid +
                ", itemDtoSet=" + itemDtoSet +
                '}';
    }
}
