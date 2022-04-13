package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
public class ProductRepository {

	private DynamicArrayList<Product> products;

	public ProductRepository() {
		this.products = new DynamicArrayList<>();
	}

	public Product save(Product product) {
		return this.products.add(product);
	}

	public Product getById(int id) {
		return this.products.get(id);
	}

	public DynamicArrayList<Product> getProducts() {
		return this.products;
	}

	public Integer count() {
		return this.products.count();
	}
}