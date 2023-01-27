package com.techelevator.inventory;
public abstract class Product{

    // encapsulated variables
    //----------------------------------------------------------------------------------
    private String slot_Location;
    private String product_Name;
    private String product_Price;

    // stock counts for each item
    //-----------------------------------------------------------------------------------
    private int product_Stock_Quantity = 5;
    private int product_Purchase_Count = 0;

    // this is max purchasable count
    private final int max_Purchase_Count = 5;

    // our product/item constructor
    //-----------------------------------------------------------------------------------
    public Product(String slot_Location, String product_Name, String product_Price) {
        this.slot_Location = slot_Location;
        this.product_Name = product_Name;
        this.product_Price = product_Price;
    }

    // stock removal and purchase count addition
    //-----------------------------------------------------------------------------------
    // this method removes one item stock per purchase
    public int remove_Stock() {

        if (product_Stock_Quantity > 0) {
            return product_Stock_Quantity --;
        }
        return product_Stock_Quantity;
    }

    // this method adds one purchase count for sales report
    public int add_PurchaseCount() {
        if (product_Stock_Quantity > 0) {
            product_Purchase_Count++;
        }
        return product_Purchase_Count;
    }

    // our getters
    //-----------------------------------------------------------------------------------
    public int getProduct_Stock_Quantity () {
        return product_Stock_Quantity;
    }

    public int getProduct_Purchase_Count() {
        return product_Purchase_Count;
    }
    public int getMax_Purchase_Count() {
        return max_Purchase_Count;
    }

    public String getSlot_Location() {
        return slot_Location;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public String getProduct_Price() {
        return product_Price;
    }

    // our setter to keep every sales report unique purchases
    //---------------------------------------------------------------------------------------
    public void setProduct_Purchase_Count(int product_Purchase_Count) {
        this.product_Purchase_Count = product_Purchase_Count;
    }

    // creating abstract class
    public abstract String sound();

    public abstract String image();
}
