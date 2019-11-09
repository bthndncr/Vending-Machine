package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
	
	public List<Product> inventoryList = new ArrayList<Product>();
	
	// initialize the inventory list via ctor
	public Inventory() {
		getInventory();
	}

	public void getInventory() {
		File file = new File("vendingmachine.csv");
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(file);
			
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineArray = line.split("\\|");
				
				Product theProduct = new Product(lineArray[0], lineArray[1], new BigDecimal(lineArray[2]), lineArray[3], 5);
				inventoryList.add(theProduct);	
			}
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}	
	}
}
