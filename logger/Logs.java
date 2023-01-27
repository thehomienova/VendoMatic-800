package com.techelevator.logger;

import com.techelevator.balance.Balance;
import com.techelevator.misc.DateAndTimeFormat;
import com.techelevator.misc.ExceptionHandling;
import com.techelevator.inventory.Product;
import com.techelevator.misc.SalesReportCalculator;

import java.io.FileOutputStream;
import java.math.BigDecimal;

public class Logs extends DateAndTimeFormat {

    //used to write our purchase logs in Log.txt
    public static void transactions_Log(Product item, Balance balance) {
        try(FileOutputStream t_log = new FileOutputStream("Log.txt", true)) {
            String line = getDate_Time() + " | PURCHASED: " + item.getProduct_Name() + " " + item.getSlot_Location() + " $" + item.getProduct_Price() + " $" + balance.getBalance().toString() + "\n";

            // prints out after selected product purchased
            t_log.write(line.getBytes());
        }
        catch (Exception e){
            ExceptionHandling.transactionLog_Exception();
        }
    }


    // used to write our money feeder logs in Log.txt
    public static void feeders_Log(Balance balance, int money) {
        try(FileOutputStream f_log = new FileOutputStream("Log.txt", true)) {
            String line = getDate_Time() + " | " + "FEED MONEY: $" + money + " $" + balance.getBalance() + "\n";

            // prints out after user feeds money
            f_log.write(line.getBytes());
        }
        catch (Exception e){
            ExceptionHandling.feederLog_Exception();
        }
    }


    // used to write our change return logs in Log.txt
    public static void changes_Log(BigDecimal current_Balance, BigDecimal balance) {

        try(FileOutputStream c_log = new FileOutputStream("Log.txt", true)) {
            String line = getDate_Time() + " | " + "GIVE CHANGE: $" + current_Balance + " $" + balance + "\n";

            // prints out after user finishes all transaction
            c_log.write(line.getBytes());

            // this means that the user chose "Finish Transaction" and used for readability
            c_log.write("- END OF TRANSACTION -\n".getBytes());
        }
        catch (Exception e){
            ExceptionHandling.changeLog_Exception();
        }
    }

    // creates a unique log for sales report with date and time
    public static void sales_Report(){

        // creating a string to make it easier to .getByte()
        String s_Report_File = "Sales Report " + getFile_Date_Time() + ".txt";

        try(FileOutputStream report_Of_Sales = new FileOutputStream(s_Report_File, true)) {

            // calculates sales amounts
            SalesReportCalculator.sales_Calculations(report_Of_Sales);
        }
        catch (Exception e){
            ExceptionHandling.salesReportLog_Exception();
        }
    }
}
