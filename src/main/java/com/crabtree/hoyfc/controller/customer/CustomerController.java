package com.crabtree.hoyfc.controller.customer;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;
	private final DynamicArrayList<Customer> customers;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
		this.customers = customerService.getCustomers();
	}

	@GetMapping
	public String showCustomers() {
		return "redirect:page/1";
	}

	@GetMapping(value = "/page/{pageNumber}")
	public String listCustomers(Model model,
	                            @PathVariable(name = "pageNumber", required = false) Integer pageNumber,
	                            @RequestParam(name = "sortColumn", required = false, defaultValue = "id") String sortColumn,
	                            @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {

		// todo: refactor to use new PaginationHelper class

		model.addAttribute("currentPageNumber", pageNumber);
		model.addAttribute("customerList", customerService.getSortedPaginatedCustomers(pageNumber, 15, sortColumn, sortDirection));
		model.addAttribute("maxPages", (int) Math.ceil((customerService.getTotalCustomers() / (double) 15)));
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		return "customers/list";
	}

	@GetMapping(value = "/search/")
	public String searchCustomers(Model model,
	                              @RequestParam(name = "term", required = true) String searchTerm,
	                              @RequestParam(name = "sortColumn", required = false, defaultValue = "id") String sortColumn,
	                              @RequestParam(name = "sortDirection", required = false, defaultValue = "asc") String sortDirection) {

		model.addAttribute("searchTerm", searchTerm);

		// todo: make this sortable and paginated
		model.addAttribute("customerList", customerService.search(searchTerm));
		model.addAttribute("maxPages", (int) Math.ceil((customerService.getTotalCustomers() / (double) 15)));
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		return "customers/search";
	}
}