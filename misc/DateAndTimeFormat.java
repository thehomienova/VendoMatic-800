package com.techelevator.misc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeFormat {


    // returns the current date and time as a string
    public static String getDate_Time() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        String s_Date = formatter.format(date);
        return s_Date;
    }

    // returns the current date and time as a string formatted to create a file
    public static String getFile_Date_Time() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh.mm.ss a");
        String s_Date = formatter.format(date);
        return s_Date;
    }
}
