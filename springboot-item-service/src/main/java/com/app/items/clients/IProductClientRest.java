package com.app.items.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.items.models.TO.ProductTO;

@FeignClient(name = "product-service")
public interface IProductClientRest {

	@GetMapping("/products")
	public List<ProductTO> list();
	
	@GetMapping("/{id}")
	public ProductTO find(@PathVariable Long id);
	
	@PostMapping("/create")
	public ProductTO save(@RequestBody ProductTO product);
	
	@PutMapping("/update")
	public ProductTO update(@RequestBody ProductTO product);
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id);
	
}
