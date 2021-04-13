package com.example.rfid.service;

public interface RfidService {

	String readRfid();//-1表示没有读成功

	String writeRfid(String rfidInfo);//-1表示没有写成功

	String resetRfid();//-1表示没有重置成功

	String writeChemicalID(String chemicalId);
}
