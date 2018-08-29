package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private long id;
    private long userId;
    private List<Item> items = new ArrayList<>();
    private double total;
    private LocalDateTime date;

    public Order(long userId, double total) {
        this.userId = userId;
        this.total = total;
    }
}
