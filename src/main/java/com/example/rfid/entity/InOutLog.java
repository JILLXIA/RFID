package com.example.rfid.entity;

public class InOutLog {
	private int id;

	private int chemical_id;

	private int i_o;

	private int is_first_add;

	private String warehouse_uuid;

	private int shelf_no;

	private int shelf_position_n;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
