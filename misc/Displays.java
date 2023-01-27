package com.techelevator.misc;

public class Displays {
    public static void welcome_Sign() {
        // this is used at the beginning when the code first runs
        System.out.println("\n" + "╔.★. .═══════════════════════════════════╗");
        System.out.println("  Welcome to our Virtual Vending Machine");
        System.out.println("╚═══════════════════════════════════. .★.╝");
    }
    public static void exit_Sign() {
        // this is used at the beginning when the code first runs
        System.out.println("\n" + "╔.★. .════════════════════════════════════════════╗");
        System.out.println("  Thank You For Using Our Virtual Vending Machine");
        System.out.println("╚════════════════════════════════════════════. .★.╝");
    }

    public static void main_Menu() {
        // prints out main menu
        System.out.println("\n" + "╔.★. .════════════╗");
        System.out.println("     Main Menu     ");
        System.out.println("╚════════════. .★.╝");
    }

    public static void display_Items_Sign() {
        // used when we list out our snacks
        System.out.println("\n" + "╔.★. .═════════════════════════════╗");
        System.out.println("       List Of All Our Snacks");
        System.out.println("╚═════════════════════════════. .★.╝"+ "\n");
    }
    public static void display_ID_Sign() {
        // used to display for purchase list
        System.out.printf("%-1s %-23s %-1s", "[ID]", "[  ITEM NAME", "PRICE ]\n");
        System.out.println("∘₊✧──────────────────────────────✧₊∘");
    }

    public static void display_Stock_Sign() {
        // used to display for display list
        System.out.printf("%-23s %s", "[       ITEM NAME", " ][ STOCK ]\n");
        System.out.println("∘₊✧──────────────────────────────✧₊∘");
    }

    public static void purchase_Menu() {
        // prints out purchase menu
        System.out.println("\n" + "╔.★. .════════════════╗");
        System.out.println("     Purchase Menu");
        System.out.println("╚════════════════. .★.╝");
    }
    public static void feed_Money_Display() {
        // prints out for our money feeder
        System.out.println("\n" + "╔.★. .═══════════════════════════╗");
        System.out.println("           Money Feeder");
        System.out.println("╚═══════════════════════════. .★.╝" + "\n");
        System.out.print("Enter Amount of Money To Feed: $");
    }
    public static void slot_Id_Input_Sign(){
        // used for our input for the ID slot codes
        System.out.print("\n" + "Please Enter The ID Code: ");
    }

    public static void out_Of_Stock_Display() {
        // used when items are out of stock
        System.out.println("\n"+"This Item Is Currently Out Of Stock!");
    }

    public static void change_Display() {
        System.out.println("\n" + "╔.★. .═════════════════╗");
        System.out.println("     Coin Dispenser     ");
        System.out.println("╚═════════════════. .★.╝");
    }
    public static void dispensing_Coins() {
        // used when coins are dispensing from the machine
        System.out.print("\n" + "Dispensing Coins");
    }

    public static void sales_Report_Display() {
        // used when creating a sales report
        System.out.println("\n" + "╔.★. .═══════════════════════════════════╗");
        System.out.println("       Creating A Sales Report File");
        System.out.println("╚═══════════════════════════════════. .★.╝");
    }
}
