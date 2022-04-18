package com.crabtree.hoyfc.controller.pickpack;

import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.service.order.OrderService;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pagination.PaginationHelper;
import com.crabtree.hoyfc.service.pickPack.PickPackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pickpack")
public class PickPackController {
	private final OrderService orderService;
	private final PickPackService pickPackService;
	private final SortHelper sortingData;

	public PickPackController(OrderService orderService, PickPackService pickPackService, SortHelper sortingData) {
		this.pickPackService = pickPackService;
		this.orderService = orderService;
		this.sortingData = sortingData;
	}

	@GetMapping
	public String showPickPack() {
		return "redirect:start";
	}

	@GetMapping("start")
	public String listPendingOrders(Model model) {

		var ph = new PaginationHelper<CustomerOrder>();
		var pendingOrders = orderService.getPendingCustomerOrdersByDateDescending();

		var pickList = new PickList();

		model.addAttribute("pickList", pickList);
		model.addAttribute("pendingOrders", pendingOrders);

		return "pickpack/start";
	}

	@PostMapping("/list")
	public String getPickList(Model model, PickList pickList) {
		var itemsToPick = pickPackService.createPickList(pickList.orderIdCollection);

		model.addAttribute("itemsToPick", itemsToPick);
		
		return "pickpack/list";
	}

	public class PickList {
		private List<Integer> orderIdCollection;

		public PickList() {
			this.orderIdCollection = new ArrayList<>();
		}

		public List<Integer> getOrderIdCollection() {
			return orderIdCollection;
		}

		public void setOrderIdCollection(List<Integer> orderIdCollection) {
			this.orderIdCollection = orderIdCollection;
		}
	}
}