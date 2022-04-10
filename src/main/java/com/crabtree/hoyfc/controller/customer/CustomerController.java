package com.crabtree.hoyfc.controller.customer;

import com.crabtree.hoyfc.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("customers", customerService.getCustomers());
		return "customers/ListCustomers";
	}
}