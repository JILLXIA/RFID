package com.example.rfid.service;

import com.example.rfid.vo.InboundVO;
import com.example.rfid.vo.OutboundVO;
import com.github.pagehelper.PageInfo;

public interface OutboundService {
	String outbound(OutboundVO outboundVO);

	PageInfo<OutboundVO> outboundList(Integer pageNo, Integer pageSize);
}
