package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BookKeeping implements Auditable {
	
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public PrintWriter writer; 
	public PrintWriter writerLog;
	Inventory inventory;
	BigDecimal totalSales = new BigDecimal("0.00");
	
	
	public BookKeeping() {
		try {
			File file = new File("Log.txt");
			file.createNewFile();
			writerLog = new PrintWriter(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// display report when  hidden 4 pressed in the main menu
	public void displayReport(Inventory inventory) {
		int soldItems;
		System.out.println("\n" + String.format("%-18s", "Item Name") + String.format("%-5s", "Sold #"));
		System.out.println("========================");
		for (Product item : inventory.inventoryList) {
			soldItems = 5 - item.getQuantity();
			System.out.println(String.format("%-20s", item.getName()) +  String.format("%-5s", soldItems));
			totalSales = totalSales.add(item.getPrice().multiply(BigDecimal.valueOf(soldItems)));
		}
		System.out.println("_______________________");
		System.out.println("\nTotal Sales: $" + totalSales);
		System.out.println("========================");
	}
	
	// writes the report to the sales.txt file
	public void report(Inventory inventory) {
		totalSales = new BigDecimal("0.00");
		File salesReport = new File("sales.txt");
		try {
			salesReport.createNewFile();
			writer = new PrintWriter(salesReport);
			int soldItems;
			for (Product item : inventory.inventoryList) {
				soldItems = 5 - item.getQuantity();
				writer.println(String.format("%-20s", item.getName() ) + " | " + soldItems);
				totalSales = totalSales.add(item.getPrice().multiply(BigDecimal.valueOf(soldItems)));
			}
			writer.println();
			writer.println("Total Sales: $" + totalSales);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*********************************************************
	 * 			Implemented Methods from Interface			 *
	 *********************************************************/

	@Override
	public void auditFeed(String insertMoney, BigDecimal balance) {
		String auditMessage = formatter.format(date);
		writerLog.println(auditMessage + " FEED MONEY: " 
				+ "$" + String.format(insertMoney, "%.2f") 
				+ " $" + String.format(balance.toString(), "%.2f"));
		
	}

	@Override
	public void auditTransaction(Product product, BigDecimal balance) {
		String auditMessage = formatter.format(date);
		writerLog.println(auditMessage + " " 
			+ product.getName() + " " 
			+ product.getProductCode()+ " $" 
			+ String.format(balance.toString(), "%.2f") 
			+ " $" + String.format(balance.subtract(product.getPrice()).toString(), "%.2f"));
	}

	@Override
	public void auditChange(BigDecimal balance) {
		String auditMessage = formatter.format(date);
		writerLog.println(auditMessage + " GIVE CHANGE: " + "$" + String.format(balance.toString(), "%.2f") + " $0.00");
	}

	@Override
	public void auditClose() {
		writer.close();
		writerLog.close();
	}
}
