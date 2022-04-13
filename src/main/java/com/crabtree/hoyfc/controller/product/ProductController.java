package com.crabtree.hoyfc.controller.product;

import com.crabtree.hoyfc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public String listProduct(Model model) {
		model.addAttribute("productList", productService.getProducts());
		return "products/list";
	}
}