package com.example.rfid.service.impl;

import com.example.rfid.dao.LabelDao;
import com.example.rfid.entity.InOutLog;
import com.example.rfid.entity.Label;
import com.example.rfid.service.LabelService;
import com.example.rfid.vo.InOutLogVO;
import com.example.rfid.vo.LabelVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class LabelServiceImpl implements LabelService {
	@Resource
	LabelDao labelDao;

	@Override
	public PageInfo<LabelVO> findAll(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Label> initLabel = labelDao.queryAllLabel();
		List<LabelVO> resultLabel = new ArrayList<>();
		for(int i = 0;i<initLabel.size();i++){
			resultLabel.add(new LabelVO(initLabel.get(i).getLabel_id(),initLabel.get(i).getIs_used(),initLabel.get(i).getChemical_id()));
		}
		return new PageInfo<>(resultLabel);
	}
}
