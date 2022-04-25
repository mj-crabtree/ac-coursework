package com.crabtree.hoyfc.controller.product;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.controller.CustomController;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.model.product.ProductStatus;
import com.crabtree.hoyfc.model.product.ProductType;
import com.crabtree.hoyfc.model.product.editProduct.EditProductFormData;
import com.crabtree.hoyfc.service.pageSort.SortDirection;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import com.crabtree.hoyfc.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController implements CustomController {
	private final ProductService productService;
	private final DynamicArrayList<Product> products;
	private final SortHelper sortingData;

	public ProductController(ProductService productService, SortHelper sortHelper) {
		this.productService = productService;
		this.products = productService.getProducts();
		this.sortingData = sortHelper;
	}

	@GetMapping
	public String showProducts() {
		this.sortingData.setSortColumn("Status");
		this.sortingData.setSortDirection(SortDirection.ASC);
		productService.sort(this.sortingData, this.products);
		return "redirect:page/1";
	}

	@GetMapping(value = "/page/{pageNumber}")
	public String listProduct(Model model, @PathVariable(value = "pageNumber") Integer pageNumber) {

		PaginationHelper<Product> ph = new PaginationHelper<>();
		var paginationData = ph.paginateCollection(this.products, this.products.count(), 15, pageNumber);

		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("productList", paginationData.getCollection());

		return "products/list";
	}

	@GetMapping(value = "/info/{productId}")
	public String viewProductInfo(Model model, @PathVariable(value = "productId") Integer productId) {

		var chosenProduct = productService.getByProductId(productId);
		var chosenProductFormData = EditProductFormData.fromProduct(chosenProduct);

		model.addAttribute("productTypesList", ProductType.values());
		model.addAttribute("productStatusList", ProductStatus.values());
		model.addAttribute("product", chosenProductFormData);

		return "products/info";
	}

	@PostMapping(value = "/info/submit")
	public String doEditExistingProduct(Model model, EditProductFormData productFormData) {

		productService.updateProduct(productFormData);
		return "redirect:/products/";
	}

	@GetMapping(value = "sort")
	// http://localhost:8080/products/sort?sortColumn=Name&sortDirection=ASC
	public String sortProducts(Model model,
	                           @RequestParam(name = "p") Integer pageNumber,
	                           @RequestParam(name = "sortColumn") String sortColumn,
	                           @RequestParam(name = "sortDirection") String sortDirection) {

		this.sortingData.setSortColumn(sortColumn);
		this.sortingData.setSortDirection(SortDirection.valueOf(sortDirection.toUpperCase()));

		productService.sort(sortingData, this.products);
		return "redirect:/products/page/" + pageNumber;
	}
}