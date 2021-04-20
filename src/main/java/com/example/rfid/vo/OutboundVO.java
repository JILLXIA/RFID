package com.example.rfid.vo;

import java.sql.Timestamp;

public class OutboundVO {
	private int label_id;

	private Timestamp create_time;

	public OutboundVO(int label_id) {
		this.label_id = label_id;
	}

	public OutboundVO(int label_id, Timestamp create_time) {
		this.label_id = label_id;
		this.create_time = create_time;
	}

	public int getLabel_id() {
		return label_id;
	}

	public void setLabel_id(int label_id) {
		this.label_id = label_id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
}
