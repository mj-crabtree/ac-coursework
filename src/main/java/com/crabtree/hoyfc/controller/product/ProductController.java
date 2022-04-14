package com.crabtree.hoyfc.controller.product;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.service.ProductService;
import com.crabtree.hoyfc.service.pageSort.SortDirection;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;
	private DynamicArrayList<Product> products;
	private SortHelper sortingData;

	public ProductController(ProductService productService, SortHelper sortHelper) {
		this.productService = productService;
		this.products = productService.getProducts();
		this.sortingData = sortHelper;
	}

	@GetMapping
	public String showProducts() {
		return "redirect:page/1";
	}

	@GetMapping(value = "/page/{pageNumber}")
	public String listProduct(Model model, @PathVariable(value = "pageNumber") Integer pageNumber) {

		var ph = new PaginationHelper();

		var paginationData = ph.paginateCollection(this.products, this.products.count(), 15, pageNumber);

		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("productList", paginationData.getCollection());

		return "products/list";
	}

	@GetMapping(value = "sort")
	// http://localhost:8080/products/sort?sortColumn=Name&sortDirection=ASC
	public String sortProducts(Model model,
	                           @RequestParam(name = "sortColumn") String sortColumn,
	                           @RequestParam(name = "sortDirection") String sortDirection) {

		this.sortingData.setSortColumn(sortColumn);
		this.sortingData.setSortDirection(SortDirection.valueOf(sortDirection.toUpperCase()));

		productService.sort(sortingData, this.products);
		return "redirect:/products/";
	}
}