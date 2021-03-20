package com.example.rfid.dao;

import com.example.rfid.entity.Init;
import com.example.rfid.vo.InitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonDao {
	int insert(Init init);

	List<Init> queryAll();

}
