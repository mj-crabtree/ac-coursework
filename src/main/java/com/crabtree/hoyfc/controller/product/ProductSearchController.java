package com.crabtree.hoyfc.controller.product;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.service.ProductService;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products/search")
public class ProductSearchController {

	private final ProductService productService;
	private DynamicArrayList<Product> searchResults;
	private final SortHelper sortingData;

	public ProductSearchController(ProductService productService, SortHelper sortHelper) {
		this.productService = productService;
		this.sortingData = sortHelper;
		this.searchResults = new DynamicArrayList<>();
	}

	@GetMapping
	public String searchProducts(Model model, @RequestParam(name = "searchTerm") String searchTerm) {

		this.searchResults = productService.search(searchTerm);

		PaginationHelper<Product> ph = new PaginationHelper<>();
		var paginationData = ph.paginateCollection(this.searchResults, this.searchResults.count(), 30, 1);

		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("productList", paginationData.getCollection());

		return "products/search";
	}
}