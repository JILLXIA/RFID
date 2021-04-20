package com.example.rfid.service.impl;

import com.example.rfid.dao.InOutLogDao;
import com.example.rfid.dao.LabelDao;
import com.example.rfid.entity.InOutLog;
import com.example.rfid.service.InboundService;
import com.example.rfid.service.RfidService;
import com.example.rfid.vo.InboundVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InboundServiceImpl implements InboundService {
	@Resource
	LabelDao labelDao;

	@Resource
	InOutLogDao inOutLogDao;

	@Resource
	RfidService rfidService;

	@Override
	public String inbound(InboundVO inboundVO) {
		//拿到chemicalid
		int chemical_id = labelDao.getChemicalId(inboundVO.getLabel_id());
		//重新写入rfid
		rfidService.writeInboundInfo(String.valueOf(inboundVO.getLabel_id()),String.valueOf(chemical_id));
		//写入in_out_log数据库,label_db
		//update TODO
		labelDao.updateisUsed(inboundVO.getLabel_id());
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);

		inOutLogDao.insertInbound(new InOutLog(inboundVO.getLabel_id(),chemical_id,1,inboundVO.getWarehouse_uuid(),
				inboundVO.getShelf_no(),inboundVO.getShelf_position_n()));

		return null;
	}

	@Override
	public PageInfo<InboundVO> inboundList(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<InOutLog> initInboundList = inOutLogDao.queryInbound();
		System.out.println("size: "+initInboundList.size());

		List<InboundVO> resultInboundList = new ArrayList<>();
		for(int i = 0;i<initInboundList.size();i++){
			resultInboundList.add(new InboundVO(initInboundList.get(i).getLabel_id(),
					initInboundList.get(i).getWarehouse_uuid(),
					initInboundList.get(i).getShelf_no(),initInboundList.get(i).getShelf_position_n(),initInboundList.get(i).getCreate_time()));
		}
		return new PageInfo<>(resultInboundList);
	}
}
