package com.example.rfid.controller;

import com.example.rfid.service.RfidReadService;
import com.example.rfid.service.RfidService;
import com.example.rfid.vo.InitVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class CommonController {
	@Resource
	private RfidReadService rfidReadService;
	@Resource
	private RfidService rfidService;
	@GetMapping("/rfid/read")
	public String rfidRead(Model model) {
//		List<Method> methodList = methodService.findAll();
//		model.addAttribute("methodList", methodList);
		rfidReadService.getRfidId();
		return "redirect:/rfid";
	}

	@GetMapping("/rfid")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
											@RequestParam(required = false, defaultValue = "10") Integer pageSize,
											Model model) {
		PageInfo<InitVO> pageInfo = rfidReadService.findAll(pageNo, pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "rfid/rfid_read_list";
	}

	@GetMapping("/rfid/test")
	public String index(Model model) {
		//rfidService.readRfid();
		return "rfid/rfid_test";
	}

	@GetMapping("/rfid/test2")
	public String index2(Model model) {
		rfidService.readRfid();
		return "rfid/rfid_test";
	}

	@GetMapping("/rfid/test3")
	public String index3(Model model) {
		//rfidService.writeRfid("1111111111111111111111111111");
		rfidService.writeRfid("2222222222222222222222222222222222222222222228");
		//rfidService.writeRfid("1111111111111111111111111111111111111111111118");
		//rfidService.writeRfid("3333333333333333333333333333");
		return "rfid/rfid_test";
	}

	@GetMapping("/rfid/test4")
	public String index4(Model model) {
		rfidService.resetRfid();
		return "rfid/rfid_test";
	}


}
