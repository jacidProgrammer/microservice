package com.app.products.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.products.models.TO.ProductTO;
import com.app.products.models.entity.Product;
import com.app.products.services.IProductService;

@RestController
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Value(value = "${server.port}")
	private int serverPort;
	
	@GetMapping("/products")
	public List<ProductTO> list() {
		return productService.findAll().stream().map( ( p ) -> {
			p.setServerPort(serverPort);
			return p; 
			} ).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public ProductTO find(@PathVariable Long id) {
		ProductTO product =  productService.findById(id);
		product.setServerPort(serverPort);
		return product;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductTO create(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductTO update(@RequestBody Product product) {
		return productService.update(product);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productService.deleteById(id);
	}

}
