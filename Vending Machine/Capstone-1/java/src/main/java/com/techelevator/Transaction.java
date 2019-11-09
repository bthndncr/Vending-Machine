package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class Transaction {
	public BigDecimal totalMoney;
	public Product theProduct;
	public BookKeeping bookKeeping;
	

	public Transaction(BigDecimal totalMoney, BookKeeping bookKeeping) {
		this.totalMoney = totalMoney;
		this.bookKeeping = bookKeeping;
	}
	
	public void insertMoney() {
		while(true) {
			System.out.println("Please insert money in either $1's, $2's, $5's, or $10's");
			Scanner scanner = new Scanner(System.in);
			String moneyInserted = scanner.nextLine();
			
			if (!moneyInserted.equals("1") 
					&& !moneyInserted.equals("2") 
					&& !moneyInserted.equals("5") 
					&& !moneyInserted.equals("10")) {
				System.out.println("\n*** Please insert only acceptable bills ***\n");
			} else {
				this.totalMoney = totalMoney.add(new BigDecimal(moneyInserted));
				bookKeeping.auditFeed(moneyInserted, this.totalMoney);
				
				break;
			}
			
		}
		System.out.println("\nCurrent Money Provided: $" + totalMoney);
	}
	
	public void yesTransactionProcess() {
		BigDecimal productPrice = theProduct.getPrice();
		// check if you have enough to purchase that item
		if (totalMoney.doubleValue() == 0 || ( totalMoney.doubleValue() < productPrice.doubleValue())) {
			System.out.println("You need " + theProduct.getPrice().subtract(totalMoney) + " more to purchase this item.");
			insertMoney();
		} else {
			transaction();
		}
		   
	}
	
	public void sound() {
		if (theProduct.getType().equals("Chip")) {
			System.out.println("Crunch Crunch, Yum!");
			//if we want to call audio file, do it here
		} else if (theProduct.getType().equals("Candy")) {
			System.out.println("Munch Munch, Yum!");
		} else if (theProduct.getType().equals("Drink")) {
			System.out.println("Glug Glug, Yum!");
		} else {
			System.out.println("Chew Chew, Yum!");
		}
	}
	
	public void transaction() {
		bookKeeping.auditTransaction(theProduct, totalMoney); // write transaction to the log 
		
		totalMoney = totalMoney.subtract(theProduct.getPrice());
		theProduct.setQuantity(theProduct.getQuantity() - 1);
		
		System.out.println("\nYour balance: $" + totalMoney);
		sound();
	}
	
	public void endTransaction() {
		
		int quarterCount = 0;
		int dimeCount = 0;
		int nickelCount = 0;
		
		bookKeeping.auditChange(totalMoney);

		while(totalMoney.doubleValue() >= 0.25) {
			totalMoney = totalMoney.subtract(new BigDecimal("0.25"));
			quarterCount++;
		}
		while(totalMoney.doubleValue() >= 0.10) {
			totalMoney = totalMoney.subtract(new BigDecimal("0.10"));
			dimeCount++;
		}
		while(totalMoney.doubleValue() >= 0.05) {
			totalMoney = totalMoney.subtract(new BigDecimal("0.05"));
			nickelCount++;
		}
		System.out.println("\nYour change is: \n" + quarterCount + " quarters, \n" + dimeCount + " dimes, and \n" + nickelCount + " nickels.");
	}
	
}