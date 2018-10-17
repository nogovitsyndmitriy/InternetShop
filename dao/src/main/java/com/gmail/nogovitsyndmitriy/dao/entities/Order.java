package com.gmail.nogovitsyndmitriy.dao.entities;

import com.gmail.nogovitsyndmitriy.dao.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "T_ORDER")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "F_ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "F_CREATED")
    private LocalDateTime created;
    @Column(name = "F_QUANTITY")
    private Integer quantity;
    @Column(name = "F_STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;
    //Items
    @ManyToOne
    @JoinColumn(name = "F_ITEM_ID")
    private Item item;
    //Users
    @ManyToOne
    @JoinColumn(name = "F_USER_ID")
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(created, order.created) &&
                Objects.equals(quantity, order.quantity) &&
                status == order.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, created, quantity, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", item=" + item +
                ", user=" + user +
                '}';
    }
}
