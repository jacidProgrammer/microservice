package com.app.items.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.app.items.clients.IProductClientRest;
import com.app.items.models.TO.ItemTO;
import com.app.items.models.TO.ProductTO;

@Service
@Primary
public class ItemServiceFeignImpl implements IItemService {
	
	@Autowired 
	private IProductClientRest clientRest;

	@Override
	public List<ItemTO> findAll() {
		return clientRest.list().stream().map( ( p ) -> new ItemTO(p, 1)).collect(Collectors.toList());
	}

	@Override
	public ItemTO findById(Long id, int amount) {
		return new ItemTO(clientRest.find(id), amount);
	}

	@Override
	public ProductTO save(ProductTO product) {
		return clientRest.save(product);
	}

	@Override
	public ProductTO update(ProductTO product) {
		return clientRest.update(product);
	}

	@Override
	public void deleteById(Long id) {
		clientRest.delete(id);
	}

}
