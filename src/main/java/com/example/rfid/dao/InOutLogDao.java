package com.example.rfid.dao;

import com.example.rfid.entity.InOutLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InOutLogDao {
	int insertInbound(InOutLog inOutLog);

	int insertOutbound(InOutLog inOutLog);

	List<InOutLog> queryInbound();

	List<InOutLog> queryOutbound();

	List<InOutLog> queryInventory();

	int deleteById(int id);
}
