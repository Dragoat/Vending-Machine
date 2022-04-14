package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;

import static com.techelevator.MachineItems.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] BACK_BUTTON = {"Back"};
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, "Exit"};
	private static final String[] PURCHASE_MENU_OPTIONS = {"Feed Money", "Select Product", "Get Change", "Finish Transaction"};



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
				itemStock.put("D4", itemStock.get("D4") - 5);
				productMenu();
				//9- this is an example of how we can update the stock
				//itemStock.put("D4", itemStock.get("D4") - 1);
				//System.out.println(itemStock.get("D4"));

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//8 - do the purchase
				choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			} else if (choice.equals("Exit")) {
				//6 - added exit code
				System.exit(3);
			}
		}
	}

	//10- Method to run the purchase menu
	private void processPurchase() throws FileNotFoundException {
		String purchaseOption = "";
		while(!purchaseOption.contentEquals("Finish Transaction")) {
			purchaseOption = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			if(purchaseOption.contentEquals("Feed Money")) {

			} else if(purchaseOption.contentEquals("Select Product")) {

			} else if(purchaseOption.contentEquals("Get Change")) {
				Transaction.makeChange(Transaction.getBalance());
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException{
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
