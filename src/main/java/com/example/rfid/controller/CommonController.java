package com.example.rfid.controller;

import com.example.rfid.service.RfidReadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller
public class CommonController {
	@Resource
	private RfidReadService rfidReadService;
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
		rfidReadService.getRfidId();
		return "rfid/rfid_read";
	}

}
