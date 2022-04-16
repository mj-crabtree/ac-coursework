package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearch;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProductRepository {

	private final DynamicArrayList<Product> products;

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

	public void replace(Product product) {
		products.put(product.getId() -1, product);
	}

	public DynamicArrayList<Product> search(String needle) {
		DynamicArrayList<Product> result = new DynamicArrayList<>();

		for (Product product : products) {
			var nameHaystack = product
					.getProductName()
					.getProductName()
					.toLowerCase(Locale.ROOT);

			var skuHaystack = product
					.getProductSku()
					.getProductSku()
					.toLowerCase();

			if (KMPSearch.search(nameHaystack, needle) == 1) {
				result.add(product);
			}
			else if (KMPSearch.search(skuHaystack, needle) == 1) {
				result.add(product);
			}
		}
		return result;
	}
}