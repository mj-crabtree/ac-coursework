package com.crabtree.hoyfc.controller.customer;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.controller.CustomController;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.service.customer.CustomerService;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers/search")
public class CustomerSearchController implements CustomController {
	private final CustomerService customerService;
	private final SortHelper sortingData;
	private DynamicArrayList<Customer> searchResults;

	public CustomerSearchController(CustomerService customerService, SortHelper sortingData) {
		this.customerService = customerService;
		this.sortingData = sortingData;
		searchResults = new DynamicArrayList<>();
	}

	@GetMapping
	public String searchCustomers(Model model, @RequestParam(name = "searchTerm") String searchTerm) {

		// http://localhost:8080/products/search/?searchTerm=lamp

		this.searchResults = customerService.search(searchTerm.trim());

		PaginationHelper<Customer> ph = new PaginationHelper<>();
		var paginationData = ph.paginateCollection(this.searchResults, this.searchResults.count(), 30, 1);

		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("customerList", paginationData.getCollection());

		return "customers/search";
	}
}