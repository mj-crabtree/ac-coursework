package com.crabtree.hoyfc.controller.order;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.controller.CustomController;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.service.customer.CustomerService;
import com.crabtree.hoyfc.service.order.OrderService;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders/search")
public class OrderSearchController implements CustomController {

	private final OrderService orderService;
	private final CustomerService customerService;
	private final SortHelper sortingData;
	private DynamicArrayList<CustomerOrder> searchResults;

	public OrderSearchController(OrderService orderService, CustomerService customerService, SortHelper sortingData) {
		this.orderService = orderService;
		this.customerService = customerService;
		this.sortingData = sortingData;
		this.searchResults = new DynamicArrayList<>();
	}

	@GetMapping
	public String searchOrders(Model model, @RequestParam(name = "searchTerm") String searchTerm) {

		PaginationHelper<CustomerOrder> ph = new PaginationHelper<>();

		this.searchResults = orderService.search(searchTerm.trim());

		var paginationData = ph.paginateCollection(this.searchResults, this.searchResults.count(), 30, 1);

		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("orderList", paginationData.getCollection());

		return "orders/search";
	}
}