package com.example.rfid.service;
import com.example.rfid.vo.LabelVO;
import com.github.pagehelper.PageInfo;

public interface LabelService {
	PageInfo<LabelVO> findAll(Integer pageNo, Integer pageSize);
}
