package com.techelevator.inventory.types;

import com.techelevator.inventory.Product;

public class Candy extends Product {

    // creates a candy object
    public Candy(String slot_Location, String product_Name, String product_Price) {
        super(slot_Location, product_Name, product_Price);
    }

    // creates candy image
    @Override
    public String image() {
        return "🍫";
    }

    // makes a candy sound
    @Override
    public String sound() {
        return "Munch Munch, Yum!";
    }
}
