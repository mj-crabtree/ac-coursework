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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	private final CustomerService customerService;
	private DynamicArrayList<CustomerOrder> orders;
	private SortHelper sortingData;

	public OrderController(OrderService orderService, CustomerService customerService, SortHelper sortHelper) {
		this.orderService = orderService;
		this.customerService = customerService;
		this.sortingData = sortHelper;
		this.orders = this.orderService.getOrders();

		// this.sortingData.setSortColumn("Date");
		// this.sortingData.setSortDirection(SortDirection.DESC);
	}

	@GetMapping
	public String showOrders() {
		return "redirect:page/1";
	}

	@GetMapping(value = "/page/{pageNumber}")
	public String listOrders(Model model, @PathVariable(value = "pageNumber") Integer pageNumber) {

		PaginationHelper<CustomerOrder> ph = new PaginationHelper<>();
		var paginationData = ph.paginateCollection(this.orders, this.orders.count(), 15, pageNumber);

		model.addAttribute("customerService", customerService);
		model.addAttribute("sortData", sortingData);
		model.addAttribute("paginationData", paginationData);
		model.addAttribute("orderList", paginationData.getCollection());
		return "orders/list";
	}


}