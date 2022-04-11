package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import static com.techelevator.view.Purchase.feedMoney;

public class Menu {
	Purchase purchase = new Purchase();
	static CompileItemInventory compile = new CompileItemInventory();
	private static PrintWriter out;
	private Scanner in;
	public static Scanner userOderInput;
	public static List<String> itemOdered;
	public static String userOder;

//ask how this constructor works
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			if(Arrays.equals(options, Purchase.PURCHASE_MENU_OPTIONS)){
				displayPurchaseMenuOptions(options);
			}else {
				displayMenuOptions(options);
			}
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.parseInt(userInput);
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
			int optionNumM = i + 1;
			out.println(optionNumM + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	private void displayPurchaseMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNumP = i + 1;
			out.println(optionNumP + ") " + options[i]);
		}
		out.println(Purchase.PURCHASE_MENU_DISPLAY_MONEY_PROVIDED + Purchase.updateCurrentMoney());

		out.flush();
	}
 // takes in money input and returns as string back to purchase.java
	public static void moneyIntake(){
		Scanner userMoneyInput = new Scanner(System.in);
		String userMoney = userMoneyInput.nextLine();
		feedMoney(userMoney);
	}

	public static void orderIntake(){
		userOderInput = new Scanner(System.in);
		userOder = userOderInput.nextLine();
		System.out.println(compile.productMap.get(userOder));

		List<String> itemOdered = compile.productMap.get(userOder);

		if(Integer.parseInt(itemOdered.get(4)) != 0) {
			int stock = Integer.parseInt(itemOdered.get(4)) - 1;
			itemOdered.set(4, String.valueOf(stock));
			compile.productMap.replace(userOder, itemOdered);

		}

		System.out.println(compile.productMap.get(userOder));

		System.out.println("\nYou bought " + itemOdered.get(1) + " for " + itemOdered.get(2));
		System.out.println(itemTypeMessage(itemOdered.get(3)));

	}

	public static void updateMap(String key, List<String> updatedList){
		compile.productMap.replace(key, updatedList);
	}

	private static String itemTypeMessage(String type){
		String message = "";

		switch (type){
			case "Gum" : message = "Chew Chew, Yum!";
				break;
			case "Chip" : message = "Crunch Crunch, Yum!";
				break;
			case "Candy" : message = "Munch Munch, Yum!";
				break;
			case "Drink" : message = "Glug Glug, Yum!";
				break;
		}

		return message;
	}
}
