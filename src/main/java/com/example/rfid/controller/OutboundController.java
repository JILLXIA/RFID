package com.example.rfid.controller;

import com.example.rfid.service.OutboundService;
import com.example.rfid.vo.InboundVO;
import com.example.rfid.vo.OutboundVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class OutboundController {
	@Resource
	OutboundService outboundService;

	@GetMapping("/outbound/add")
	public String outboundAddPage(Model model) {
		return "outbound/outbound_add";
	}

	@PostMapping("/outboundPost")
	public String index(@RequestParam("label_id") String label_id){
		outboundService.outbound(new OutboundVO(Integer.parseInt(label_id)));

		return "redirect:/outbound";
	}

	@GetMapping("/outbound")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
											@RequestParam(required = false, defaultValue = "10") Integer pageSize,
											Model model) {
		PageInfo<OutboundVO> pageInfo = outboundService.outboundList(pageNo, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "outbound/outbound_list";
	}
}
