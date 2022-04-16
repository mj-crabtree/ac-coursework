package com.crabtree.hoyfc.controller.product;

import com.crabtree.hoyfc.model.product.ProductStatus;
import com.crabtree.hoyfc.model.product.ProductType;
import com.crabtree.hoyfc.model.product.createProduct.CreateProductFormData;
import com.crabtree.hoyfc.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/newproduct")
public class CreateProductController {

	private final ProductService productService;

	public CreateProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public String showCreateProductPage(Model model) {
		var newProduct = new CreateProductFormData();

		model.addAttribute("newProduct", newProduct);
		model.addAttribute("productStatusList", ProductStatus.values());
		model.addAttribute("productTypesList", ProductType.values());
		return "products/create";
	}

	@PostMapping("submit")
	public String doCreateNewProduct(CreateProductFormData newProduct) {
		var product = productService.makeProduct(newProduct.toParameters());
		productService.save(product);
		return "redirect:/products/";
	}
}