package com.example.rfid.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class InOutLog {
	private int id;

	private int label_id;

	private int chemical_id;

	private int i_o;//1:入库，0：出库，-1：未初始化

	private String warehouse_uuid;

	private int shelf_no;

	private int shelf_position_n;

	private Timestamp create_time;

	public InOutLog(int label_id, int chemical_id, int i_o) {
		this.label_id = label_id;
		this.chemical_id = chemical_id;
		this.i_o = i_o;
	}

	public InOutLog(int label_id, int chemical_id, int i_o, String warehouse_uuid, int shelf_no, int shelf_position_n) {
		this.label_id = label_id;
		this.chemical_id = chemical_id;
		this.i_o = i_o;
		this.warehouse_uuid = warehouse_uuid;
		this.shelf_no = shelf_no;
		this.shelf_position_n = shelf_position_n;
	}

	public InOutLog(int id, int label_id, int chemical_id, int i_o, String warehouse_uuid, int shelf_no, int shelf_position_n) {
		this.id = id;
		this.label_id = label_id;
		this.chemical_id = chemical_id;
		this.i_o = i_o;
		this.warehouse_uuid = warehouse_uuid;
		this.shelf_no = shelf_no;
		this.shelf_position_n = shelf_position_n;
	}

	public InOutLog(int label_id, int chemical_id, int i_o, String warehouse_uuid, int shelf_no, int shelf_position_n, Timestamp create_time) {
		this.label_id = label_id;
		this.chemical_id = chemical_id;
		this.i_o = i_o;
		this.warehouse_uuid = warehouse_uuid;
		this.shelf_no = shelf_no;
		this.shelf_position_n = shelf_position_n;
		this.create_time = create_time;
	}

	public InOutLog(int id, int label_id, int chemical_id, int i_o, String warehouse_uuid, int shelf_no, int shelf_position_n, Timestamp create_time) {
		this.id = id;
		this.label_id = label_id;
		this.chemical_id = chemical_id;
		this.i_o = i_o;
		this.warehouse_uuid = warehouse_uuid;
		this.shelf_no = shelf_no;
		this.shelf_position_n = shelf_position_n;
		this.create_time = create_time;
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

	public int getI_o() {
		return i_o;
	}

	public void setI_o(int i_o) {
		this.i_o = i_o;
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

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
}
