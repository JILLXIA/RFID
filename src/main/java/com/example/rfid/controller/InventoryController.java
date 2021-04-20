package com.example.rfid.controller;

import com.example.rfid.entity.InOutLog;
import com.example.rfid.service.InventoryService;
import com.example.rfid.vo.InOutLogVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class InventoryController {
	@Resource
	private InventoryService inventoryService;
	/**
	 * 盘点
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/inventory")
	public String queryAll(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
												 @RequestParam(required = false, defaultValue = "10") Integer pageSize,
												 Model model) {
		PageInfo<InOutLogVO> pageInfo = inventoryService.findAll(pageNo, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "inventory/inventory";
	}

	@GetMapping("/inventory/delete")
	public String ruleAddPage(@RequestParam("id") int id) {
		inventoryService.deleteById(id);
		return "redirect:/inventory";
	}
}
