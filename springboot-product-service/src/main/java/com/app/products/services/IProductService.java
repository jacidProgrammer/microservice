package com.app.products.services;

import java.util.List;

import com.app.products.models.TO.ProductTO;
import com.app.products.models.entity.Product;

public interface IProductService {

	public List<ProductTO> findAll();
	public ProductTO findById(long id);
	public ProductTO save(Product product);
	public ProductTO update(Product product);
	public void deleteById(long id);
}
