package com.app.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.products.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
