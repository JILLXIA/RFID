package com.example.rfid.dao;

import com.example.rfid.entity.InOutLog;
import com.example.rfid.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelDao {
	int insert(Label label);

	int update(Label label);

	int deleteById(int label_id);

	List<Label> queryAllLabel();

	int getChemicalId(int label_id);

	int updateisUsed(int label_id);
}
