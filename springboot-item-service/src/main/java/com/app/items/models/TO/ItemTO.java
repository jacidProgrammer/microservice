package com.app.items.models.TO;

public class ItemTO {

	private ProductTO product;
	private int amount;

	public ItemTO() {

	}

	public ItemTO(ProductTO product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	public ProductTO getProduct() {
		return product;
	}

	public void setProduct(ProductTO product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getPrice() {
		return product.getPrice();
	}

}
