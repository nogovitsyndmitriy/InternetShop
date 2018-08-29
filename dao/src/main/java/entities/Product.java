package entities;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class    Product {

    private long id;
    private String category;
    private String name;
    private double price;
    private int quantity;

    public Product(String category, String name, double price) {
        this.category = category;
        this.name = name;
        this.price = price;

    }
}
