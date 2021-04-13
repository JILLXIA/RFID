package com.example.rfid.controller;

import com.example.rfid.service.RfidService;
import com.example.rfid.vo.InOutLogVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class NewLabelController {

	@Resource
	RfidService rfidService;
	/**
	 * 录入标签
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/label/add")
	public String labelAddPage(Model model) {
		return "newlabel/label_add";
	}

	@PostMapping("/label")
	public String index(@RequestParam("chemical_id") String chemical_id){
		rfidService.writeChemicalID(chemical_id);
		return "redirect:/label/add";
	}
}
