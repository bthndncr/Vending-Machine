package com.techelevator;

import java.math.BigDecimal;



public class Product {
	
	private String productCode;
	private String name;
	private BigDecimal price;
	private String type;
	private int quantity;
	
	
	public Product(String productCode, String name, BigDecimal price, String type, int quantity) {
		this.productCode = productCode;
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
	}


	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", name=" + name + ", price=" + price + ", type=" + type
				+ ", quantity=" + quantity + "]";
	}
	
	

		
	
}
