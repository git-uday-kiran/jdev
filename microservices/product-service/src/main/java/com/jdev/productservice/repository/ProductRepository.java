package com.jdev.productservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.jdev.productservice.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
}
