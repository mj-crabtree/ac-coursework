package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearchImpl;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.Locale;

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

			if (KMPSearchImpl.knuthMorrisPratt(nameHaystack, needle) == 1) {
				result.add(product);
			}
			else if (KMPSearchImpl.knuthMorrisPratt(skuHaystack, needle) == 1) {
				result.add(product);
			}
		}
		return result;
	}
}