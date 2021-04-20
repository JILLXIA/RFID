package com.example.rfid.vo;

public class LabelVO {
	public int getLabel_id() {
		return label_id;
	}

	public void setLabel_id(int label_id) {
		this.label_id = label_id;
	}

	public String getIs_used() {
		return is_used;
	}

	public void setIs_used(String is_used) {
		this.is_used = is_used;
	}

	public int getChemical_id() {
		return chemical_id;
	}

	public void setChemical_id(int chemical_id) {
		this.chemical_id = chemical_id;
	}

	public LabelVO(int label_id, int is_used, int chemical_id) {
		this.label_id = label_id;
		this.is_used = is_used==1? "已使用":"未使用";
		this.chemical_id = chemical_id;
	}

	private int label_id;

	private String is_used;

	private int chemical_id;

}
