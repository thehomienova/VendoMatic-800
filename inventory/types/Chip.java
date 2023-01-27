package com.techelevator.inventory.types;

import com.techelevator.inventory.Product;

public class Chip extends Product {

    // creates a chip object
    public Chip(String slot_Location, String product_Name, String product_Price) {
        super(slot_Location, product_Name, product_Price);
    }

    // creates chip image
    @Override
    public String image() {
        return "🍘";
    }

    // makes a chip sound
    @Override
    public String sound() {
        return "Crunch Crunch, Yum!";
    }

}
