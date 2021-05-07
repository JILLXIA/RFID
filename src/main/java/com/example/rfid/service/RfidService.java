package com.example.rfid.service;

import com.example.rfid.utils.rfid.NoRfidReadException;

public interface RfidService {

	String readRfid();//-1表示没有读成功

	int readChemicalId() throws NoRfidReadException;

	int readCarrierId() throws NoRfidReadException;

	boolean writeChemicalIdNew(String chemicalId) throws NoRfidReadException;

	String writeRfid(String rfidInfo);//-1表示没有写成功

	String resetRfid(String label_id);//-1表示没有重置成功

	String resetEmptyEPC();

	String resetEmptyUSER();

	String writeChemicalID(String label_id,String chemicalId);

	String writeChemicalID(String chemicalId);

	String writeInboundInfo(String label_id,String chemicalId);

	String writeOutboundInfo(String label_id,String chemicalId);
}
