package com.example.rfid.controller;

import com.example.rfid.service.InventoryService;
import com.example.rfid.service.LabelService;
import com.example.rfid.service.RfidService;
import com.example.rfid.vo.InOutLogVO;
import com.example.rfid.vo.LabelVO;
import com.github.pagehelper.PageInfo;
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

	@Resource
	InventoryService inventoryService;

	@Resource
	LabelService labelService;

	@GetMapping("/label")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
											@RequestParam(required = false, defaultValue = "10") Integer pageSize,
											Model model) {
		PageInfo<LabelVO> pageInfo = labelService.findAll(pageNo, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "newlabel/label_list";
	}

	@GetMapping("/label/add")
	public String labelAddPage(Model model) {
		return "newlabel/label_add";
	}

	@GetMapping("/label/empty")
	public String labelEmptyPage(Model model) {
		return "newlabel/label_empty";
	}

	@PostMapping("/labelPost")
	public String index(@RequestParam("label_id") String label_id,
											@RequestParam("chemical_id") String chemical_id){
		rfidService.writeChemicalID(label_id,fill_id(chemical_id));
		return "redirect:/label";
	}

	@GetMapping("/writeChemicalID2RFID")
	public String writeChemicalID2RFID(
						@RequestParam("chemicalID") int chemicalID){
		String chemical_id = chemicalID+"";
		String res = rfidService.writeChemicalID(fill_id(chemical_id));
		return res.equals("1")?"success":"failure";
	}

	@GetMapping("/writeCarrierID2RFID")
	public String writeCarrierID2RFID(
			@RequestParam("chemicalID") int carrierID){
		String carrier_id = carrierID+"";
		String res = rfidService.writeChemicalID(fill_id(carrier_id));
		return res.equals("1")?"success":"failure";
	}

	@PostMapping("/label/emptyLabel")
	public String emptyLabel(@RequestParam("label_id") String label_id){
		rfidService.resetRfid(label_id);
		return "redirect:/label";
	}

	public static String fill_id(String id){
		if(id.length()==10){
			return id;
		}
		while(id.length()!=10){
			id = "0"+id;
		}
		return id;
	}

}
