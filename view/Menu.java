package com.techelevator.view;

import com.techelevator.balance.Balance;
import com.techelevator.logger.Logs;
import com.techelevator.misc.Displays;
import com.techelevator.misc.ExceptionHandling;

import java.io.*;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	// this is used for the sales report hidden option in main menu
	//----------------------------------------------------------------------------------------------
	public Object getChoiceFromOptionsWithSalesReport(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptionsWithSalesReport(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private void displayMenuOptionsWithSalesReport(Object[] options) {
		out.println();
		for (int i = 0; i < options.length-1; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	// using menu user input to add balance to customer
	//-----------------------------------------------------------------------------------------------
	public void balance (Balance customer_balance){

		// display of money feeder
		Displays.feed_Money_Display();

		// this checks to see if the input is a whole number
		try {
			int money = Integer.parseInt(customer_Input());

			// adds money from user input into balance
			customer_balance.add(money);

			// creates a return change log into our log.txt
			Logs.feeders_Log(customer_balance, money);
		}
		catch (NumberFormatException e) {
			// if the user input isn't a whole number, it will print out a string
			ExceptionHandling.parseInt_Exception();
		}
	}

	// used for ID code input
	//------------------------------------------------------------------------------------------------
	public String customer_Input () {
		String userInput = in.nextLine();
		return userInput.toUpperCase();
	}
}
