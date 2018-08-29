package entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private long id;
    private String productName;
    private long productId;
    private int quantity;
    private long orderId;
    private long userId;
    private double price;


    public Item(String productName, int quantity, long productId, long userId, long orderId,  double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.price = price;
    }
}
