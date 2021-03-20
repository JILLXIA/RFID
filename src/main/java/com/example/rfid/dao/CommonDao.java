package com.example.rfid.dao;

import com.example.rfid.entity.Init;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonDao {
	int insert(Init init);
}
