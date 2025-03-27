package com.app.items.services;

import java.util.List;

import com.app.items.models.TO.ItemTO;
import com.app.items.models.TO.ProductTO;

public interface IItemService {
	
	public List<ItemTO> findAll();
	public ItemTO findById(Long id, int amount);
	public ProductTO save(ProductTO product);
	public ProductTO update(ProductTO product);
	public void deleteById(Long id);
}
