package com.crabtree.hoyfc.controller.order;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.service.CustomerService;
import com.crabtree.hoyfc.service.OrderService;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders/search")
public class OrderSearchController {

	private final OrderService orderService;
	private final CustomerService customerService;
	private SortHelper sortingData;
	private DynamicArrayList<CustomerOrder> searchResults;

	public OrderSearchController(OrderService orderService, CustomerService customerService, SortHelper sortingData) {
		this.orderService = orderService;
		this.customerService = customerService;
		this.sortingData = sortingData;
		this.searchResults = new DynamicArrayList<>();
	}

	@GetMapping
	public String searchOrders(Model model, @RequestParam(name = "searchTerm") String searchTerm) {

		this.searchResults = orderService.search(searchTerm);

		PaginationHelper<CustomerOrder> ph = new PaginationHelper<>();
		var paginationData = ph.paginateCollection(this.searchResults, this.searchResults.count(), 30, 1);

		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("productList", paginationData.getCollection());

		return "products/search";
	}
}