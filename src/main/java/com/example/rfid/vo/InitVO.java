package com.example.rfid.vo;

public class InitVO {
	private int id;

	private String send_rfid_id;

	private String receive_rfid_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSendRfid_id() {
		return send_rfid_id;
	}

	public void setSendRfid_id(String rfid_id) {
		this.send_rfid_id = rfid_id;
	}

	public String getReceiveRfid_id() {
		return receive_rfid_id;
	}

	public void setReceiveRfid_id(String rfid_id) {
		this.receive_rfid_id = rfid_id;
	}
}
