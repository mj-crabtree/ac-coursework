package com.crabtree.hoyfc.controller.product;

import com.crabtree.hoyfc.service.ProductService;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = "/page/{pageNumber}")
	public String listProduct(Model model, @PathVariable(value = "pageNumber") Integer pageNumber) {

		var ph = new PaginationHelper();

		var products = productService.getProducts();
		var paginationData = ph.paginationHelper(products, products.count(), 15, pageNumber);

		model.addAttribute("paginationHelper", paginationData);
		model.addAttribute("productList", paginationData.getCollection());

		return "products/list";
	}
}