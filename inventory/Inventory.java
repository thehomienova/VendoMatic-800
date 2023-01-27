package com.techelevator.inventory;

import com.techelevator.balance.Balance;
import com.techelevator.inventory.types.Candy;
import com.techelevator.inventory.types.Chip;
import com.techelevator.inventory.types.Drink;
import com.techelevator.inventory.types.Gum;
import com.techelevator.logger.Logs;
import com.techelevator.misc.Displays;
import com.techelevator.VendingMachineCLI;
import com.techelevator.misc.ExceptionHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class Inventory {

    // global map of products associated to slot location
    public static Map<String, Product> items_Grabber = new TreeMap<>();

    // inventory file reader
    // --------------------------------------------------------------------------
    public static void data_Reader(String file) {

        // reads the file
        File fileInput = new File(file);

        // checks to see if file is eligible
        try (Scanner dataInput = new Scanner(fileInput)) {

            // our loop to read each line in the file
            while (dataInput.hasNextLine()) {

                // creates a list of arrays of a string that is split by a pipe "|"
                List<String> product_Info = Arrays.asList(dataInput.nextLine().split("\\|"));

                // creating variables with names
                String id_Slot = product_Info.get(0);
                String product_Name = product_Info.get(1);
                String product_Price = product_Info.get(2);
                String product_Type = product_Info.get(3);

                // creates new objects by type
                object_Type_create(product_Type, id_Slot, product_Name, product_Price);

                // adds slot location as key, and then product as the value into a map called items grabber
//                items_Grabber.put(id_Slot, product);
            }

            // if the file does not have a path, it will catch the error and print out a string
        } catch (FileNotFoundException e) {
            ExceptionHandling.fileException();
        }
    }

    // object creator
    //----------------------------------------------------------------------------------
    public static void object_Type_create(String product_Type, String id_Slot, String product_Name, String product_Price) {
        // creates new chip object
        if (product_Type.equals("Chip")) {
            items_Grabber.put(id_Slot, new Chip(id_Slot, product_Name, product_Price));
        }
        // creates new candy object
        else if (product_Type.equals("Candy")) {
            items_Grabber.put(id_Slot, new Candy(id_Slot, product_Name, product_Price));
        }
        // creates new drink object
        else if (product_Type.equals("Drink")) {
            items_Grabber.put(id_Slot, new Drink(id_Slot, product_Name, product_Price));
        }
        // creates new gum object
        else if (product_Type.equals("Gum")) {
            items_Grabber.put(id_Slot, new Gum(id_Slot, product_Name, product_Price));
        }
    }

    // Item Display Lists for Display Items/Select Purchase
    //-------------------------------------------------------------------------------------
    public static void items_Display(String choice) {

        // this is a for loop that goes through every item
        for(Product p : items_Grabber.values()) {

            // if choice is on display items, it will print out item name/stock amount
            if(choice.equals(VendingMachineCLI.getMainMenuOptionDisplayItems())) {

                // displays list of items with stocked quantity
                display_Items_List(p);
            }

            // if choice is on select product, it will print out slot location/item name/item price
            else if (choice.equals(VendingMachineCLI.getPurchaseOptionSelectProduct())) {

                // displays purchasable items with IDs and prices
                purchase_Items_List(p);
            }
        }
    }

    // displays list of items with stocked quantity
    public static void display_Items_List(Product item) {

        System.out.printf("%-23s %-1s", "[ " + item.getProduct_Name(), " ]");
        // prints out each product name
//        System.out.println("Item: " + item.getProduct_Name());

        // if the quantity of stock is 0, it will print out a string
        if (item.getProduct_Stock_Quantity()==0) {
            System.out.println(" SOLD OUT");
        }

        // if not, then it will print out the quantity
        else {
            System.out.println("[   " + item.getProduct_Stock_Quantity()+ "   ]");
        }
    }

    // displays purchasable items with IDs and prices
    public static void purchase_Items_List(Product item) {

        // prints sold out when out of stock
        if (item.getProduct_Stock_Quantity()==0) {
            System.out.printf("%-1s %-28s %-1s","[" + item.getSlot_Location() + "]", "[ " + "Sold Out", /*"$"+ item.getProduct_Price() */ " ]" + "\n");
        }

        // prints out the slot location, item name, and item cost
        else {
            System.out.printf("%-1s %-23s %-1s","[" + item.getSlot_Location() + "]", "[ " + item.getProduct_Name(), "$"+ item.getProduct_Price() + " ]" + "\n");
        }
    }

    // this is our method when purchasing items
    //-------------------------------------------------------------------------------
    public static void item_Selector(String choice, Balance balance){

        // if there is a key, then it will calculate balance with cost
        if(items_Grabber.containsKey(choice)) {
            purchasing_Item(balance, items_Grabber.get(choice));
        }

        // if the key is null within the map, it will print out a string
        else {
            System.out.println("\n" + "ID Code: [" + choice + "] Does Not Exist!");
        }
    }

    // method used to calculate balance and change stock quantity
    public static void purchasing_Item(Balance balance, Product item) {

        // changing the string of item cost to a big decimal
        BigDecimal price_value = BigDecimal.valueOf(Double.parseDouble(item.getProduct_Price()));

        // if the stock quantity is 0, then it will print out a string
        if (item.getProduct_Stock_Quantity()==0){
            Displays.out_Of_Stock_Display();
        }

        // if the stock quantity is greater than 0, it will calculate money
        else if (item.getProduct_Stock_Quantity() > 0) {

            // this calculates remaining balance and prints out strings
            dispenser(balance, item, price_value);
        }
    }

    // this method is for after entering the slot location, our dispenser
    public static void dispenser(Balance balance, Product item, BigDecimal price_value) {

        // checks to see if balance will still be greater than 0
        if (balance.getBalance().compareTo(price_value) >= 0) {

            // this subtracts item cost from current balance
            balance.subtract(price_value);

            // this adds one count after purchase to sales report
            item.add_PurchaseCount();

            // this removes one stock from the stock quantity
            item.remove_Stock();

            // prints out item name, cost, and remaining balance
            System.out.println("\n" + "Dispensing Your Item: " + item.getProduct_Name());
            System.out.println("Cost: $" + item.getProduct_Price());
            System.out.println("Remaining Balance: $"+ balance.getBalance());

            // prints out sound and image by type of item
            System.out.println("\n" + item.image() + " " + item.sound());

            // creates a transaction log to our log.txt
            Logs.transactions_Log(item, balance);
        }

        // if there is not enough money, prints message
        else {
            System.out.println("\n" + "You are short of $" + price_value.subtract(balance.getBalance()) + ". Feed More Money!");
        }
    }
}

