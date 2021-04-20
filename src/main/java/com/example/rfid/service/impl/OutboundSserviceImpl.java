package com.example.rfid.service.impl;

import com.example.rfid.dao.InOutLogDao;
import com.example.rfid.dao.LabelDao;
import com.example.rfid.entity.InOutLog;
import com.example.rfid.service.OutboundService;
import com.example.rfid.service.RfidService;
import com.example.rfid.vo.InboundVO;
import com.example.rfid.vo.OutboundVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutboundSserviceImpl implements OutboundService {
	@Resource
	LabelDao labelDao;

	@Resource
	RfidService rfidService;

	@Resource
	InOutLogDao inOutLogDao;

	@Override
	public String outbound(OutboundVO outboundVO) {
		//chemical_id
		int chemical_id = labelDao.getChemicalId(outboundVO.getLabel_id());

		//写入rfid
		rfidService.writeOutboundInfo(String.valueOf(outboundVO.getLabel_id()),String.valueOf(chemical_id));

		//更新in_out_log数据库
		inOutLogDao.insertOutbound(new InOutLog(outboundVO.getLabel_id(),chemical_id,0));

		return null;
	}

	@Override
	public PageInfo<OutboundVO> outboundList(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<InOutLog> initOutboundList = inOutLogDao.queryOutbound();
		//System.out.println("size: "+initOutboundList.size());

		List<OutboundVO> resultOutboundList = new ArrayList<>();
		for(int i = 0;i<initOutboundList.size();i++){
			resultOutboundList.add(new OutboundVO(initOutboundList.get(i).getLabel_id(),initOutboundList.get(i).getCreate_time()));
		}
		return new PageInfo<>(resultOutboundList);
	}
}
