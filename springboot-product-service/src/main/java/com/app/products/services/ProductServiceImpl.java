package com.app.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.products.models.TO.ProductTO;
import com.app.products.models.entity.Product;
import com.app.products.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	public ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ProductTO> findAll() {
		return StreamSupport.stream(productRepository.findAll().spliterator(), true)
				.map( ( p ) -> new ProductTO(p) )
			    .collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public ProductTO findById(long id) {
		return new ProductTO(productRepository.findById(id).orElse(new Product()));
	}

	@Override
	@Transactional
	public ProductTO save(Product product) {
		return new ProductTO(productRepository.save(product));
	}
	
	@Override
	@Transactional
	public ProductTO update(Product productChanged) {
		Optional<Product> optionaProduct = productRepository.findById(productChanged.getId());
		if(optionaProduct.isPresent()) {
			Product product = optionaProduct.get();
			product.setName(productChanged.getName());
			product.setPrice(productChanged.getPrice());
			return save(product);
		} else {
			return new ProductTO();
		}
	}

	@Override
	@Transactional
	public void deleteById(long id) {
		productRepository.deleteById(id);
	}

}
