package com.example.rfid.vo;

public class InitVO {
	private int id;

	private String send_rfid_id;

	private String receive_rfid_id;

	public InitVO(int id, String send_rfid_id, String receive_rfid_id){
		this.id = id;
		this.send_rfid_id = send_rfid_id;
		this.receive_rfid_id = receive_rfid_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSend_rfid_id() {return send_rfid_id;}

	public void setSend_rfid_id(String send_rfid_id) {
		this.send_rfid_id = send_rfid_id;
	}

	public String getReceive_rfid_id() {
		return receive_rfid_id;
	}

	public void setReceive_rfid_id(String receive_rfid_id) {
		this.receive_rfid_id = receive_rfid_id;
	}
}
