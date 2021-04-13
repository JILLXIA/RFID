package com.example.rfid.dao;

import com.example.rfid.entity.InOutLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InOutLogDao {
	int insert(InOutLog inOutLog);

	List<InOutLog> queryInbound();

	List<InOutLog> queryInventory();
}
