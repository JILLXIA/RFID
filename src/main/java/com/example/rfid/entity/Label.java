package com.example.rfid.entity;

public class Label {
	private int label_id;

	private int is_used;

	private int chemical_id;

	public Label(int label_id, int is_used, int chemical_id) {
		this.label_id = label_id;
		this.is_used = is_used;
		this.chemical_id = chemical_id;
	}

	public int getLabel_id() {
		return label_id;
	}

	public void setLabel_id(int label_id) {
		this.label_id = label_id;
	}

	public int getIs_used() {
		return is_used;
	}

	public void setIs_used(int is_used) {
		this.is_used = is_used;
	}

	public int getChemical_id() {
		return chemical_id;
	}

	public void setChemical_id(int chemical_id) {
		this.chemical_id = chemical_id;
	}
}
