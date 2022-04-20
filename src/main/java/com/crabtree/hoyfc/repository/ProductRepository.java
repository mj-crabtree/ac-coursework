package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearch;
import com.crabtree.customDSA.algorithms.search.recursiveBinarySearch.RecursiveBinarySearch;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.model.product.StockCount;
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

	public Product getByIndex(int id) {
		return this.products.getByIndex(id);
	}

	public DynamicArrayList<Product> getProducts() {
		return this.products;
	}

	public Integer count() {
		return this.products.count();
	}

	public void update(Product product) {
		products.put(product.getId() - 1, product);
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

	public void deductFromStockCount(Integer productId, Integer quantity) {
		var product = getByIndex(productId -1);
		var oldCount = product
				.getStockCount().getCurrentStock();

		var newcount = oldCount -= quantity;

		product.setStockCount(new StockCount(newcount, product
				.getStockCount()
				.getRestockTrigger()));

		update(product);
	}

	public Product getByProductId(Integer productId) {
		var bs = new RecursiveBinarySearch();
		return getByIndex(bs.productIdSearch(this.products, productId));
	}
}