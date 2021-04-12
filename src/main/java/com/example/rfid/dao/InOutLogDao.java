package com.example.rfid.dao;

import com.example.rfid.entity.InOutLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InOutLogDao {
	int insert(InOutLog inOutLog);
}
