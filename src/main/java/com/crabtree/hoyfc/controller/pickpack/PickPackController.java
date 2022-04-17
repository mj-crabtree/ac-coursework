package com.crabtree.hoyfc.controller.pickpack;

import com.crabtree.hoyfc.service.pickPack.PickPackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pickpack")
public class PickPackController {
	private final PickPackService pickPackService;

	public PickPackController(PickPackService pickPackService) {
		this.pickPackService = pickPackService;
	}

	@GetMapping
	public String showPickPack() {
		return "redirect:start";
	}

	@GetMapping("start")
	public String showOrderSelection(Model model) {
		return "pickpack/start";
	}
}