package com.example.rfid.service;

import com.example.rfid.vo.InboundVO;
import com.example.rfid.vo.LabelVO;
import com.github.pagehelper.PageInfo;

public interface InboundService {
	String inbound(InboundVO inboundVO);

	PageInfo<InboundVO> inboundList(Integer pageNo, Integer pageSize);
}
