package com.example.rfid.vo;

import com.example.rfid.entity.InOutLog;

import java.sql.Timestamp;

public class InOutLogVO {
	private int id;

	private int label_id;

	private int chemical_id;

	private String i_o;//1:入库，0：出库，-1：未初始化

	private String warehouse_uuid;

	private String shelf_no;

	private String shelf_position_n;

	private Timestamp create_time;

	public InOutLogVO(InOutLog inOutLog) {
		this.id = inOutLog.getId();
		this.label_id = inOutLog.getLabel_id();
		this.chemical_id = inOutLog.getChemical_id();
		switch(inOutLog.getI_o()){
			case 0:
				this.i_o = "出库";
				break;
			case 1:
				this.i_o = "入库";
				break;
			case -1:
				this.i_o = "未初始化";
				break;
		}
		if(inOutLog.getWarehouse_uuid().equals("-1")){
			this.warehouse_uuid = "N/A";
		}else{
			this.warehouse_uuid = inOutLog.getWarehouse_uuid();
		}

		if(inOutLog.getShelf_no()==-1){
			this.shelf_no = "N/A";
		}else{
			this.shelf_no = String.valueOf(inOutLog.getShelf_no());
		}

		if(inOutLog.getShelf_position_n()==-1){
			this.shelf_position_n = "N/A";
		}else{
			this.shelf_position_n = String.valueOf(inOutLog.getShelf_position_n());
		}


		this.create_time = inOutLog.getCreate_time();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLabel_id() {
		return label_id;
	}

	public void setLabel_id(int label_id) {
		this.label_id = label_id;
	}

	public int getChemical_id() {
		return chemical_id;
	}

	public void setChemical_id(int chemical_id) {
		this.chemical_id = chemical_id;
	}

	public String getI_o() {
		return i_o;
	}

	public void setI_o(String i_o) {
		this.i_o = i_o;
	}



	public String getWarehouse_uuid() {
		return warehouse_uuid;
	}

	public void setWarehouse_uuid(String warehouse_uuid) {
		this.warehouse_uuid = warehouse_uuid;
	}

	public String getShelf_no() {
		return shelf_no;
	}

	public void setShelf_no(String shelf_no) {
		this.shelf_no = shelf_no;
	}

	public String getShelf_position_n() {
		return shelf_position_n;
	}

	public void setShelf_position_n(String shelf_position_n) {
		this.shelf_position_n = shelf_position_n;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
}
