package com.example.rfid.service.impl;

import com.example.rfid.rfidInterface.rfidInteraction.RXTXListener;
import com.example.rfid.rfidInterface.rfidUtil.StringTool;

public class RXTXListenerImpl implements RXTXListener {
	public static String str = "";

	@Override
	public void reciveData(byte[] btAryReceiveData) {
		str = StringTool.byteArrayToString(btAryReceiveData, 0, btAryReceiveData.length);
		System.out.println("reciveData: " + str);
		//str = StringTool.byteArrayToString(btAryReceiveData, 0, btAryReceiveData.length);
	}

	@Override
	public void sendData(byte[] btArySendData) {
		System.out.println("sendData: " + StringTool.byteArrayToString(btArySendData, 0, btArySendData.length));
	}

	@Override
	public void onLostConnect() {

	}

}
