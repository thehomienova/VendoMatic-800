package com.techelevator.inventory.types;

import com.techelevator.inventory.Product;

public class Drink extends Product {

    // creates a drink object
    public Drink(String slot_Location, String product_Name, String product_Price) {
        super(slot_Location, product_Name, product_Price);
    }

    // create drink image
    @Override
    public String image() {
        return "🍹";
    }

    // makes a drink sound
    @Override
    public String sound() {
        return "Glug Glug, Yum!";
    }

}
