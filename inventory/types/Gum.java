package com.techelevator.inventory.types;

import com.techelevator.inventory.Product;

public class Gum extends Product {
    public Gum(String slot_Location, String product_Name, String product_Price) {
        super(slot_Location, product_Name, product_Price);
    }

    //  makes candy image
    @Override
    public String image() {
        return "🍬";
    }

    // makes a gum sound
    @Override
    public String sound() {
        return "Chew Chew, Yum!";
    }

}
