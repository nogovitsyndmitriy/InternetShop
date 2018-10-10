package com.gmail.nogovitsyndmitriy.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
@Table(name = "T_DISCOUNT")
public class Discount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID")
    private Long id;
    @Column(name = "F_NAME")
    private String name;
    @Column(name = "F_PERCENT")
    private BigDecimal percent;
    @Column(name = "F_VALID")
    private LocalDateTime valid;


    @ManyToMany
    @JoinTable(
            name = "T_ITEM_DISCOUNT",
            joinColumns = {@JoinColumn(name = "F_DISCOUNT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "F_ITEM_ID")}
    )
    Set<Item> items = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount)) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id) &&
                Objects.equals(name, discount.name) &&
                Objects.equals(percent, discount.percent) &&
                Objects.equals(valid, discount.valid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, percent, valid);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percent=" + percent +
                ", valid=" + valid +
                ", items=" + items +
                '}';
    }
}
