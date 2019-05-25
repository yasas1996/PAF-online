package com.paf.n3ag6.models;



public class Add {
	private int id;
	private String category_name;
	private double price;
	private int quantity;
	
	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}
	
	public String getcategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	public double getprice() {
		return price;
	}

	public void setprice(double price) {
		this.price = price;
	}
	
	public int getquantity() {
		return quantity;
	}

	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
	
}