package com.crabtree.hoyfc.controller.order;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.service.customer.CustomerService;
import com.crabtree.hoyfc.service.order.OrderService;
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
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	private final CustomerService customerService;
	private final DynamicArrayList<CustomerOrder> orders;
	private final SortHelper sortingData;

	public OrderController(OrderService orderService, CustomerService customerService, SortHelper sortHelper) {
		this.orderService = orderService;
		this.customerService = customerService;
		this.sortingData = sortHelper;
		this.orders = this.orderService.getOrders();
	}

	@GetMapping
	public String showOrders() {
		this.sortingData.setSortColumn("Id");
		this.sortingData.setSortDirection(SortDirection.DESC);
		orderService.sort(this.sortingData);
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

	@GetMapping(value = "/info/{orderId}")
	public String viewOrderInfo(Model model, @PathVariable(value = "orderId") Integer orderId) {
		var order = orderService.getOrderById(orderId);
		// var searched = orderService.binarySearch(orderId);

		model.addAttribute("order", order);
		model.addAttribute("orderItems", order.getLineItems());
		return "orders/info";
	}

	@GetMapping(value = "sort")
	public String sortOrders(Model model,
	                         @RequestParam(name = "p") Integer pageNumber,
	                         @RequestParam(name = "sortColumn") String sortColumn,
	                         @RequestParam(name = "sortDirection") String sortDirection) {
		this.sortingData.setSortColumn(sortColumn);
		this.sortingData.setSortDirection(SortDirection.valueOf(sortDirection.toUpperCase()));
		orderService.sort(sortingData);
		return "redirect:/orders/page/" + pageNumber;
	}
}