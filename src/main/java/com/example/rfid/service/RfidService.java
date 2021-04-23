package com.example.rfid.service;

public interface RfidService {

	String readRfid();//-1表示没有读成功

	String writeRfid(String rfidInfo);//-1表示没有写成功

	String resetRfid(String label_id);//-1表示没有重置成功

	String writeChemicalID(String label_id,String chemicalId);

	String writeChemicalID(String chemicalId);

	String writeInboundInfo(String label_id,String chemicalId);

	String writeOutboundInfo(String label_id,String chemicalId);
}
