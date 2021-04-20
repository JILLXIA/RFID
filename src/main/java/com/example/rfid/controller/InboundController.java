package com.example.rfid.controller;

import com.example.rfid.service.InboundService;
import com.example.rfid.vo.InboundVO;
import com.example.rfid.vo.LabelVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class InboundController {
	@Resource
	InboundService inboundService;

	@GetMapping("/inbound/add")
	public String inboundAddPage(Model model) {
		return "inbound/inbound_add";
	}

	@PostMapping("/inboundPost")
	public String index(@RequestParam("label_id") String label_id,
											@RequestParam("warehouse_uuid") String warehouse_uuid,
											@RequestParam("shelf_no") String shelf_no,
											@RequestParam("shelf_position_n") String shelf_position_n){
		inboundService.inbound(new InboundVO(Integer.parseInt(label_id),warehouse_uuid,Integer.parseInt(shelf_no),
				Integer.parseInt(shelf_position_n)));

		return "redirect:/inbound";
	}

	@GetMapping("/inbound")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
											@RequestParam(required = false, defaultValue = "10") Integer pageSize,
											Model model) {
		PageInfo<InboundVO> pageInfo = inboundService.inboundList(pageNo, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "inbound/inbound_list";
	}
}
