package com.app.products.models.TO;

import java.io.Serializable;
import java.time.LocalDate;

import com.app.products.models.entity.Product;

public class ProductTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private long id;
	private String name;
	private double price;
	private LocalDate creationDate;
	private int serverPort;
	
	public ProductTO() {

	}
	
	public ProductTO(Product product) {
		setName(product.getName());
		setPrice(product.getPrice());
		setCreationDate(product.getCreationDate());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

}
