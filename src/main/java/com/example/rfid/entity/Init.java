package com.example.rfid.entity;

public class Init {
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

	public void setSendRfid_id(String send_rfid_id) {
		this.send_rfid_id = send_rfid_id;
	}

	public String getReceiveRfid_id() {
		return receive_rfid_id;
	}

	public void setReceiveRfid_id(String receive_rfid_id) {
		this.receive_rfid_id = receive_rfid_id;
	}
}
