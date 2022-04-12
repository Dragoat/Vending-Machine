package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;

import static com.techelevator.MachineItems.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String EXIT_BUTTON = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT_BUTTON};



	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}



	public void run() throws FileNotFoundException {
		//5- running the vending machine file
		MachineItems.readFile();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//7 - display vending machine items from file and with map
				for(String f : itemCode.keySet()) {
					System.out.println(f + "| " + itemName.get(f) + "| $" + itemPrice.get(f) + "| " + itemStock.get(f) + " in stock");
				}
				//9- this is an example of how we can update the stock
				//itemStock.put("D4", itemStock.get("D4") - 1);
				//System.out.println(itemStock.get("D4"));

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//8 - do the purchase
			} else if (choice.equals(EXIT_BUTTON)) {
				//6 - added exit code
				System.exit(3);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException{
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
