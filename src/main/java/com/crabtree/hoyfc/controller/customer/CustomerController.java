package com.crabtree.hoyfc.controller.customer;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.service.customer.CustomerService;
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
@RequestMapping("/customers")
public class CustomerController {
	private final CustomerService customerService;
	private final DynamicArrayList<Customer> customers;
	private final SortHelper sortingData;

	public CustomerController(CustomerService customerService, SortHelper sortingData) {
		this.customerService = customerService;
		this.customers = customerService.getCustomers();
		this.sortingData = sortingData;
	}

	@GetMapping
	public String showCustomers() {
		return "redirect:page/1";
	}

	@GetMapping(value = "/page/{pageNumber}")
	public String listCustomers(Model model, @PathVariable(name = "pageNumber") Integer pageNumber) {

		PaginationHelper<Customer> ph = new PaginationHelper<>();
		var paginationData = ph.paginateCollection(this.customers, this.customers.count(), 15, pageNumber);

		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("customerList", paginationData.getCollection());

		return "customers/list";
	}

	@GetMapping(value = "sort")
	public String sortCustomers(Model model,
	                            @RequestParam(name = "p") Integer pageNumber,
	                            @RequestParam(name = "sortColumn") String sortColumn,
	                            @RequestParam(name = "sortDirection") String sortDirection) {

		this.sortingData.setSortColumn(sortColumn);
		this.sortingData.setSortDirection(SortDirection.valueOf(sortDirection.toUpperCase()));

		customerService.sort(sortingData, this.customers);
		return "redirect:/customers/page/" + pageNumber;
	}
}