package com.example.rfid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	@GetMapping("/method/add")
	public String methodAddPage(Model model) {
//		List<Method> methodList = methodService.findAll();
//		model.addAttribute("methodList", methodList);
		return "method/method_add";
	}

	@GetMapping("/rfid/read")
	public String rfidRead(Model model) {
//		List<Method> methodList = methodService.findAll();
//		model.addAttribute("methodList", methodList);
		return "rfid/rfid_read";
	}

}
