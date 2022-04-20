package com.crabtree.hoyfc.service.product;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.model.product.comparatorFactory.ProductComparatorFactory;
import com.crabtree.hoyfc.model.product.createProduct.CreateProductParameters;
import com.crabtree.hoyfc.model.product.editProduct.EditProductFormData;
import com.crabtree.hoyfc.repository.ProductRepository;
import com.crabtree.hoyfc.service.factory.ModelFactory;
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

	public Product makeProduct(CreateProductParameters parameters) {
		var newProduct = ModelFactory.newProduct();

		newProduct.setId(parameters.getProductId());
		newProduct.setProductName(parameters.getProductName());
		newProduct.setProductDescription(parameters.getProductDescription());
		newProduct.setStockCount(parameters.getStockCount());
		newProduct.setProductSku(parameters.getProductSku());
		newProduct.setProductType(parameters.getProductType());
		newProduct.setProductStatus(parameters.getProductStatus());
		newProduct.setProductPrice(parameters.getProductPrice());

		return newProduct;
	}

	public DynamicArrayList<Product> getProducts() {
		return productRepository.getProducts();
	}

	public void save(Product newProduct) {
		productRepository.save(newProduct);
	}

	public void sort(SortHelper sortingData, DynamicArrayList<Product> data) {
		var comparator = ProductComparatorFactory.getComparator(sortingData.getSortColumn(), sortingData.getSortDirection());
		insertionSort.sort(data, comparator);
	}

	public DynamicArrayList<Product> search(String searchTerm) {
		return productRepository.search(searchTerm);
	}

	public Product getProductByIndex(int index) {
		return productRepository.getByIndex(index);
	}

	public void updateProduct(EditProductFormData productFormData) {
		var product = makeProduct(productFormData.toParameters());
		productRepository.update(product);
	}

	public void deductFromStockCount(Integer productId, Integer quantity) {
		this.productRepository.deductFromStockCount(productId, quantity);
	}

	public Product getByProductId(Integer productId) {
		return this.productRepository.getByProductId(productId);
	}
}