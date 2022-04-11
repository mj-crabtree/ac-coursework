package com.crabtree.hoyfc.controller.customer;

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

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public String index() {
		return "redirect:page/1?sortColumn=id&sortDirection=asc";
	}

	@GetMapping(value = "/page/{pageNumber}")
	public String listCustomers(Model model,
								@PathVariable(name = "pageNumber") Integer pageNumber,
	                            @RequestParam(name = "sortColumn") String sortColumn,
	                            @RequestParam(name = "sortDirection") String sortDirection) {

		model.addAttribute("currentPageNumber", pageNumber);
		model.addAttribute("customerList", customerService.getPaginatedCustomers(pageNumber, 15, sortColumn, sortDirection));
		model.addAttribute("maxPages", Math.ceil((customerService.getTotalCustomers() / (double) 15)));
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

		return "customers/ListCustomers";
	}
}