package support.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
//Centralized User object that we used for all scenarios: checkout, inventory, shopping cart
public class User {
    String username;
    String name;
    String lastName;
    int zipcode;
    List<Product> product;
}
