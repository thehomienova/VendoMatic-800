package com.techelevator.misc;

import com.techelevator.inventory.Inventory;
import com.techelevator.inventory.Product;

import java.io.FileOutputStream;
import java.math.BigDecimal;

public class SalesReportCalculator {

    // does calculations to print out sales amounts
    public static void sales_Calculations(FileOutputStream report_Of_Sales) {
        try {
            // collects all purchased items' costs
            BigDecimal sales = BigDecimal.valueOf(0).setScale(2);

            // creating indent to look better
            String indent = "                    ";

            // for every product of the list
            for (Product p : Inventory.items_Grabber.values()) {

                // each product has an indent
                String item_Name = p.getProduct_Name();
                item_Name += indent.substring(0, indent.length() - p.getProduct_Name().length());

                // prints out product name and purchase count
                String line = item_Name + "| " + p.getProduct_Purchase_Count() + "\n";

                // writes the line of the string
                report_Of_Sales.write(line.getBytes());

                // for every count up to five it adds cost to sales
                for (int i = 1; i <= p.getMax_Purchase_Count(); i++) {
                    if (p.getProduct_Purchase_Count() >= i){
                        sales = sales.add(BigDecimal.valueOf(Double.parseDouble(p.getProduct_Price())).setScale(2));
                    }
                }
            }

            // prints out the total sale cost
            String sales_Total = "\n**TOTAL SALES**     $" + sales;
            report_Of_Sales.write(sales_Total.getBytes());
        }

        // catches if there is a problem with writing to a file
        catch (Exception e) {
            ExceptionHandling.salesReportLog_Exception();
        }
    }

    // sets the sold item counts back to 0
    public static void set_To_Zero () {
        for (Product p : Inventory.items_Grabber.values()) {
            p.setProduct_Purchase_Count(0);
        }
    }
}
