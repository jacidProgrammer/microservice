package com.app.items.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.items.models.TO.ItemTO;
import com.app.items.models.TO.ProductTO;
import com.app.items.services.IItemService;

@RefreshScope
@RestController
public class Itemcontroller {
	
	public static final String ENVIRONMENT = "environment";
	public static final String PROFILE = "profile";

	@Autowired
	public IItemService itemService;
	
	@Value("${environment}")
	private String env;
	
	@Value("${spring.profiles.active:}")
	private String profile;
	
	@GetMapping("/items")
	public List<ItemTO> list() {
		return itemService.findAll();
	}
	
	//@HystrixCommand(fallbackMethod = "productNotFound")
	@GetMapping("/{id}/{amount}")
	public ItemTO find(@PathVariable long id, @PathVariable int amount) {
		return itemService.findById(id, amount);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductTO create(@RequestBody ProductTO product) {
		return itemService.save(product);
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductTO update(@RequestBody ProductTO product) {
		return itemService.update(product);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.deleteById(id);
	}
	
	public ItemTO productNotFound(long id, int amount) {
		ProductTO product = new ProductTO();
		product.setName("Unknow");
		return new ItemTO(product, amount);
	}
	
	@GetMapping("/config")
	public ResponseEntity<?> getConfig() {
		Map<String, String> output = new HashMap<>();
		output.put(ENVIRONMENT, env);
		output.put(PROFILE, profile.toString());
		return new ResponseEntity<Map<String, String>>(output, HttpStatus.OK);
	}
}
