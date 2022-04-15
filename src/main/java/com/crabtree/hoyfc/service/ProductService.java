package com.crabtree.hoyfc.service;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.model.product.comparatorFactory.ProductComparatorFactory;
import com.crabtree.hoyfc.model.product.createProduct.CreateProductParameters;
import com.crabtree.hoyfc.model.service.modelFactory.ModelFactory;
import com.crabtree.hoyfc.repository.ProductRepository;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductIdService productIdService;
	private final InsertionSort insertionSort;

	public ProductService(ProductRepository productRepository, ProductIdService productIdService, InsertionSort insertionSort) {
		this.productRepository = productRepository;
		this.productIdService = productIdService;
		this.insertionSort = insertionSort;
	}

	public void createProduct(CreateProductParameters parameters) {
		var newProduct = ModelFactory.newProduct();

		newProduct.setId(ProductIdService.getNext());
		newProduct.setProductName(parameters.getProductName());
		newProduct.setProductDescription(parameters.getProductDescription());
		newProduct.setStockCount(parameters.getStockCount());
		newProduct.setProductSku(parameters.getProductSku());
		newProduct.setProductType(parameters.getProductType());
		newProduct.setProductStatus(parameters.getProductStatus());
		newProduct.setProductPrice(parameters.getProductPrice());

		productRepository.save(newProduct);
	}

	public DynamicArrayList<Product> getProducts() {
		return productRepository.getProducts();
	}

	public void sort(SortHelper sortingData, DynamicArrayList<Product> data) {
		var comparator = ProductComparatorFactory.getComparator(sortingData.getSortColumn(), sortingData.getSortDirection());
		insertionSort.sort(data, comparator);
	}

	public DynamicArrayList<Product> search(String searchTerm) {
		return productRepository.search(searchTerm);
	}

	public Product getProductByIndex(int index) {
		// todo: refactor me
		return productRepository.getById(index);
	}
}