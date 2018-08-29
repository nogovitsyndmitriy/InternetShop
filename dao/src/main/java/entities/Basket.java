package entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    private LinkedList<Item> basket;

}
