package com.app.items.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.items.models.TO.ItemTO;
import com.app.items.models.TO.ProductTO;

@Service
public class ItemServiceImpl implements IItemService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<ItemTO> findAll() {
		List<ProductTO> products = Arrays.asList(restTemplate.getForObject("http://product-service/products", ProductTO[].class));
		return products.stream().map( ( p ) -> new ItemTO(p, 1)).collect(Collectors.toList());
	}

	@Override
	public ItemTO findById(Long id, int amount) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		ProductTO product = restTemplate.getForObject("http://product-service/{id}", ProductTO.class, params);
		return new ItemTO(product, amount);
	}

	@Override
	public ProductTO save(ProductTO product) {
		HttpEntity<ProductTO> body = new HttpEntity<>(product);
		ResponseEntity<ProductTO> response = restTemplate.exchange("http://product-service/create", HttpMethod.POST, body, ProductTO.class);
		
		if(response != null) {
			return response.getBody();
		}
		return new ProductTO();
	}

	@Override
	public ProductTO update(ProductTO product) {
		HttpEntity<ProductTO> body = new HttpEntity<>(product);
		ResponseEntity<ProductTO> response = restTemplate.exchange("http://product-service/update", HttpMethod.PUT, body, ProductTO.class);

		if(response != null) {
			return response.getBody();
		}
		return new ProductTO();
	}

	@Override
	public void deleteById(Long id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		restTemplate.delete("http://product-service/delete/{id}", params);
	}

}
