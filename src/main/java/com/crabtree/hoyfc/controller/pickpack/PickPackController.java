package com.crabtree.hoyfc.controller.pickpack;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.service.order.OrderService;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import com.crabtree.hoyfc.service.pickPack.PickPackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private DynamicArrayList<CustomerOrder> pickedOrders;

	public PickPackController(OrderService orderService, PickPackService pickPackService, SortHelper sortingData) {
		this.pickPackService = pickPackService;
		this.orderService = orderService;
		this.sortingData = sortingData;
		this.pickedOrders = new DynamicArrayList<>();
	}

	@GetMapping
	public String showPickPack() {
		return "redirect:start";
	}

	@GetMapping("start")
	public String listPendingOrders(Model model) {

		var pendingOrders = orderService.getPendingCustomerOrdersByDateDescending();
		var pickList = new PickList();
		model.addAttribute("pickList", pickList);
		model.addAttribute("pendingOrders", pendingOrders);

		return "pickpack/start";
	}

	@PostMapping("/list")
	public String getPickList(Model model, PickList pickList) {
		var itemsToPick = pickPackService.createPickList(pickList.getOrderIdCollection());
		this.pickedOrders = pickPackService.getPickedOrders(pickList);

		PickedThingsDto pickedForm = new PickedThingsDto();
		for (int i = 0; i < pickedOrders.count(); i++) {
			pickedForm.add(new PickThing(0, 0));
		}

		model.addAttribute("form", pickedForm);
		model.addAttribute("itemsToPick", itemsToPick);

		return "pickpack/list";
	}

	@PostMapping("/commit")
	public String commitPickedList(Model model, @ModelAttribute PickedThingsDto form) {

		System.out.println();
		return null;
	}

	public static class PickThing {
		private Integer id;
		private Integer quantity;

		public PickThing() {
		}

		public PickThing(int id, int quantity) {
			this.id = id;
			this.quantity = quantity;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
	}

	public static class PickedThingsDto {
		private List<PickThing> items;

		public PickedThingsDto(List<PickThing> items) {
			this.items = items;
		}

		public PickedThingsDto() {
			this.items = new ArrayList<>();
		}

		public List<PickThing> getItems() {
			return items;
		}

		public void setItems(List<PickThing> items) {
			this.items = items;
		}

		public void add(PickThing thing) {
			this.items.add(thing);
		}
	}

}