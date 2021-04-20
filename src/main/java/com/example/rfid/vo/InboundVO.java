package com.example.rfid.vo;

import java.sql.Timestamp;

public class InboundVO {
	private int label_id;

	private String warehouse_uuid;

	private int shelf_no;

	private int shelf_position_n;

	private Timestamp create_time;

	public InboundVO(int label_id, String warehouse_uuid, int shelf_no, int shelf_position_n) {
		this.label_id = label_id;
		this.warehouse_uuid = warehouse_uuid;
		this.shelf_no = shelf_no;
		this.shelf_position_n = shelf_position_n;
	}

	public InboundVO(int label_id, String warehouse_uuid, int shelf_no, int shelf_position_n, Timestamp create_time) {
		this.label_id = label_id;
		this.warehouse_uuid = warehouse_uuid;
		this.shelf_no = shelf_no;
		this.shelf_position_n = shelf_position_n;
		this.create_time = create_time;
	}

	public int getLabel_id() {
		return label_id;
	}

	public void setLabel_id(int label_id) {
		this.label_id = label_id;
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
