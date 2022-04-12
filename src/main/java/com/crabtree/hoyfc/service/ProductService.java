package com.crabtree.hoyfc.service;

import com.crabtree.hoyfc.model.modelFactory.ModelFactory;
import com.crabtree.hoyfc.model.product.createProduct.CreateProductParameters;
import com.crabtree.hoyfc.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductIdService productIdService;

	public ProductService(ProductRepository productRepository, ProductIdService productIdService) {
		this.productRepository = productRepository;
		this.productIdService = productIdService;
	}

	public void createProduct(CreateProductParameters parameters) {
		var newProduct = ModelFactory.newProduct();
	}
}