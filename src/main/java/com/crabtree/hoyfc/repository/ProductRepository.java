package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearch;
import com.crabtree.customDSA.algorithms.search.recursiveBinarySearch.RecursiveBinarySearch;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.model.product.StockCount;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProductRepository implements CustomRepository {

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
		return needle.startsWith("FC-") ? searchBySku(needle) : searchByProductName(needle);
	}

	private DynamicArrayList<Product> searchByProductName(String needle) {
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

			if (KMPSearch.search(nameHaystack, needle.toLowerCase()) == 1) {
				result.add(product);
			}
			else if (KMPSearch.search(skuHaystack, needle.toLowerCase()) == 1) {
				result.add(product);
			}
		}
		return result;
	}

	private DynamicArrayList<Product> searchBySku(String needle) {
		DynamicArrayList<Product> result = new DynamicArrayList<>();

		for (Product product : products) {
			var skuHaystack = product
					.getProductSku()
					.getProductSku()
					.toLowerCase();

			if (KMPSearch.search(skuHaystack, needle.toLowerCase()) == 1) {
				result.add(product);
			}
		}
		return result;
	}

	public Product getByProductId(Integer productId) {
		var bs = new RecursiveBinarySearch();
		return getByIndex(bs.productIdSearch(this.products, productId));
	}

	public void deductFromStockCount(Integer productId, Integer quantity) {
		var product = getByProductId(productId);
		var oldCount = product
				.getStockCount()
				.getCurrentStock();
		var rt = product
				.getStockCount()
				.getRestockTrigger();


		var newcount = oldCount - quantity;
		var newStockCount = new StockCount(newcount, rt);
		product.setStockCount(newStockCount);

		update(product);
	}
}