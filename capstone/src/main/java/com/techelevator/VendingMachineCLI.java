package com.techelevator;

import com.sun.nio.sctp.AbstractNotificationHandler;
import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

import static com.techelevator.MachineItems.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] BACK_BUTTON = {"Back"};
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, "Exit"};
	private static final String[] PURCHASE_MENU_OPTIONS = {"Feed Money", "Select Product", "Get Change", "Finish Transaction"};
	private static  final String[] CASH_INPUT_OPTIONS = {"$1", "$2", "$5", "$10", "Back"};



	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	//7 - display vending machine items from file and with map
	public void productMenu() {
		for(String f : itemCode.keySet()) {
			if (itemStock.get(f) == 0) {
				System.out.println(f + "| " + itemName.get(f) + "| $" + itemPrice.get(f) + "| SOLD OUT");
			} else {
				System.out.println(f + "| " + itemName.get(f) + "| $" + itemPrice.get(f) + "| " + itemStock.get(f) + " in stock");
			}
		}
		//only show the back button and not the whole main menu
		String choice = (String) menu.getChoiceFromOptions(BACK_BUTTON);
	}


	public void run() throws FileNotFoundException {
		//5- running the vending machine file
		MachineItems.readFile();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//displaying the product menu
				productMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//8 - do the purchase
				purchaseMenu();
			} else if (choice.equals("Exit")) {
				//6 - added exit code
				//spot for receipt
				System.exit(3);
			}
		}
	}

	//10- Method to run the purchase menu
	private void purchaseMenu() throws FileNotFoundException {
		String purchaseOption = "";
		while(!purchaseOption.contentEquals("Finish Transaction")) {
			purchaseOption = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			if(purchaseOption.contentEquals("Feed Money")) {
				customerMoneyFeed();
			} else if(purchaseOption.contentEquals("Select Product")) {
				productSelection();
			} else if(purchaseOption.contentEquals("Get Change")) {
				Transaction.makeChange(Transaction.getBalance());
			}
		}
		if(purchaseOption.contentEquals("Finish Transaction")) {
			Transaction.makeChange(Transaction.getBalance());
		}
	}

	//giving the customer their money options
	private void customerMoneyFeed() throws FileNotFoundException {
		String moneyOptions = "";
		while(!moneyOptions.contentEquals("Back")) {
			moneyOptions = (String) menu.getChoiceFromOptions(CASH_INPUT_OPTIONS);
			System.out.println(moneyOptions);
			Transaction.feedCash(moneyOptions);
		}
	}

	//user input product selection from menu
	private void productSelection() throws FileNotFoundException {
		Set<String> itemCodeKeys = itemCode.keySet();
		for (String key : itemCodeKeys) {
			if (itemStock.get(key) == 0) {
				System.out.println(key + "| " + itemName.get(key) + "| $" + itemPrice.get(key) + "| OUT OF STOCK");
			} else {
				System.out.println(key + "| " + itemName.get(key) + "| $" + itemPrice.get(key) + "| " + itemStock.get(key) + " in stock");
			}
		}
		System.out.println("\n1) Back" + "\nCurrent Money Provided: $" + Transaction.getBalance() + "\n" + "\nPlease Make a Selection >>> ");
		Scanner userInput = new Scanner(System.in);
		String itemCode = userInput.nextLine();
		//back button for the user
		if (itemCode.equals("1")) {
				purchaseMenu();
			} else {
				//transaction
				Transaction.buySnacks(itemCode);
			}
	}

	public static void main(String[] args) throws FileNotFoundException{
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
