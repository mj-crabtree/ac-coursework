package com.crabtree.hoyfc.controller.index;

import com.crabtree.hoyfc.controller.CustomController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController implements CustomController {
	@GetMapping
	public String index() {
		return "redirect:/orders/";
	}
}