package com.example.rfid.service.impl;

import com.example.rfid.dao.InOutLogDao;
import com.example.rfid.entity.InOutLog;
import com.example.rfid.service.InventoryService;
import com.example.rfid.vo.InOutLogVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImp implements InventoryService {
	@Resource
	InOutLogDao inOutLogDao;

	@Override
	public PageInfo<InOutLogVO> findAll(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<InOutLog> initInOutLog = inOutLogDao.queryInventory();
		List<InOutLogVO> resultInOutLog = new ArrayList<>();
		for(int i = 0;i<initInOutLog.size();i++){
			resultInOutLog.add(new InOutLogVO(initInOutLog.get(i)));
		}
		return new PageInfo<>(resultInOutLog);
	}

	@Override
	public boolean deleteById(int id) {
		return this.inOutLogDao.deleteById(id) > 0;
	}
}
