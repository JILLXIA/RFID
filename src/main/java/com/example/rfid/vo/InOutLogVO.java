package com.example.rfid.vo;

import com.example.rfid.entity.InOutLog;

public class InOutLogVO {
	private int id;

	private String chemical_id;

	private String i_o;//1:入库，0：出库，-1：未初始化

	private int is_first_add;

	public InOutLogVO(String chemical_id) {
		this.chemical_id = chemical_id;
	}

	public InOutLogVO(InOutLog inOutLog) {
		this.id = inOutLog.getId();
		this.chemical_id = String.valueOf(inOutLog.getChemical_id());
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
		this.is_first_add = inOutLog.getIs_first_add();
		this.warehouse_uuid = inOutLog.getWarehouse_uuid();
		this.shelf_no = inOutLog.getShelf_no();
		this.shelf_position_n = inOutLog.getShelf_position_n();
	}

	private String warehouse_uuid;

	private int shelf_no;

	private int shelf_position_n;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChemical_id() {
		return chemical_id;
	}

	public void setChemical_id(String chemical_id) {
		this.chemical_id = chemical_id;
	}

	public String getI_o() {
		return i_o;
	}

	public void setI_o(String i_o) {
		this.i_o = i_o;
	}

	public int getIs_first_add() {
		return is_first_add;
	}

	public void setIs_first_add(int is_first_add) {
		this.is_first_add = is_first_add;
	}

	public String getWarehouse_uuid() {
		return warehouse_uuid;
	}

	public void setWarehouse_uuid(String warehouse_uuid) {
		this.warehouse_uuid = warehouse_uuid;
	}

	public int getShelf_no() {
		return shelf_no;
	}

	public void setShelf_no(int shelf_no) {
		this.shelf_no = shelf_no;
	}

	public int getShelf_position_n() {
		return shelf_position_n;
	}

	public void setShelf_position_n(int shelf_position_n) {
		this.shelf_position_n = shelf_position_n;
	}
}
