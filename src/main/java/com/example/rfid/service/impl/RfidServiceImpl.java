package com.example.rfid.service.impl;

import com.example.rfid.dao.InOutLogDao;
import com.example.rfid.dao.LabelDao;
import com.example.rfid.entity.InOutLog;
import com.example.rfid.entity.Label;
import com.example.rfid.service.RfidService;
import com.example.rfid.utils.rfid.RfidDeviceUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service
public class RfidServiceImpl implements RfidService {
	@Resource
	InOutLogDao inOutLogDao;

	@Resource
	LabelDao labelDao;

	public static String findTty() {
		String path = "/dev";		//要遍历的路径
		File file = new File(path);		//获取其file对象
		File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
		for(File f:fs){					//遍历File[]数组
			//System.out.println(f.getName());
			if(f.getName().matches("tty.usbserial-(.*)"))	{	//若非目录(即文件)，则打印
				return f.getName();
			}
		}
		return null;
	}

	@Override
	public String readRfid() {
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		String dataUSER = RfidDeviceUtil.readUSER();
		RfidDeviceUtil.resetReadUser();
		System.out.println("dataUSER: "+dataUSER);
		return dataUSER;
	}

	public int getChemicalIdFromUser(String dataUSER){
		String[] user = dataUSER.split(" ");
		int head = 8;

		return 0;
	}

	@Override
	public String writeRfid(String rfid) {
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);

		String testUSER = rfid.substring(0, 32);
		boolean success1 = RfidDeviceUtil.writeUSER(testUSER, 32);
		RfidDeviceUtil.resetWriteUser();


		if (success1) {
			return rfid;
		}
		return "-1";
	}

	@Override
	public String resetRfid(String label_id) {
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		boolean success = RfidDeviceUtil.resetUser();
		//boolean success = RfidDeviceUtil.resetEPC();
		// TODO 写一个update 方法
		if(success){
			//labelDao.update(new Label(Integer.parseInt(label_id),0,0));
			labelDao.deleteById(Integer.parseInt(label_id));
			labelDao.insert(new Label(Integer.parseInt(label_id),0,0));
		}

		return success==true ? "1" : "0";
	}

	@Override
	public String writeChemicalID(String label_id,String chemicalId){
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);

		boolean success = RfidDeviceUtil.writeUSER(chemicalId, 10);

		if(success){
			labelDao.insert(new Label(Integer.parseInt(label_id),0,Integer.parseInt(chemicalId)));
		}

		return success==true ? "1" : "0";
	}

	@Override
	public String writeInboundInfo(String label_id,String chemicalId){
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		String result = chemicalId+"1";
		boolean success = RfidDeviceUtil.writeUSER(result, 11);


		return success==true ? "1" : "0";
	}

	@Override
	public String writeOutboundInfo(String label_id, String chemicalId) {
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		String result = chemicalId+"0";
		boolean success = RfidDeviceUtil.writeUSER(result, 11);


		return success==true ? "1" : "0";
	}

}

