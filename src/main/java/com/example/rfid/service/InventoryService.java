package com.example.rfid.service;

import com.example.rfid.vo.InOutLogVO;
import com.example.rfid.vo.InitVO;
import com.github.pagehelper.PageInfo;

public interface InventoryService {
	/**
	 * 分页查询
	 * @param pageNo 页码
	 * @param pageSize 页大小
	 * @return
	 */
	PageInfo<InOutLogVO> findAll(Integer pageNo, Integer pageSize);

	boolean deleteById(int id);
}
