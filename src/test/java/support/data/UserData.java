package support.data;

import support.model.Product;
import support.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserData {
    //Default constants that we'll use over and over.
    public static final String DEFAULT_USERNAME = "standard_user";
    private static final String DEFAULT_FIRST_NAME = "Eli";
    private static final String DEFAULT_LAST_NAME = "Sandoval";
    private static final int DEFAULT_ZIP_CODE = 1414;
    private static final String SAUCE_LABS_BACKPACK = "Sauce Labs Backpack";
    private static final String SAUCE_LABS_BIKE_LIGHT = "Sauce Labs Bike Light";
    private static final String SAUCE_LABS_BOLT_TSHIRT = "Sauce Labs Bolt T-Shirt";

    //Object mother for basic user with valid info
    public static User newValidUser() {
        return new User(
                DEFAULT_USERNAME,
                DEFAULT_FIRST_NAME,
                DEFAULT_LAST_NAME,
                DEFAULT_ZIP_CODE,
                Collections.singletonList(newValidProduct(SAUCE_LABS_BACKPACK, 29.99))
        );
    }

    //Object mother for basic user with valid info and multiple products
    public static User newValidUserWithListOfProducts() {
        return new User(
                DEFAULT_USERNAME,
                DEFAULT_FIRST_NAME,
                DEFAULT_LAST_NAME,
                DEFAULT_ZIP_CODE,
                newValidListOfProducts()
        );
    }


    public static Product newValidProduct(String name, double price) {
        return new Product(name, price);
    }

    public static List<Product> newValidListOfProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(newValidProduct(SAUCE_LABS_BACKPACK, 29.99));
        productList.add(newValidProduct(SAUCE_LABS_BIKE_LIGHT, 9.99));
        productList.add(newValidProduct(SAUCE_LABS_BOLT_TSHIRT, 15.99));

        return productList;
    }
}
