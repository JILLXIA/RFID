package com.example.rfid.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.rfid.dao.InOutLogDao;
import com.example.rfid.dao.LabelDao;
import com.example.rfid.entity.Carrier;
import com.example.rfid.entity.Chemical;
import com.example.rfid.entity.InOutLog;
import com.example.rfid.entity.Label;
import com.example.rfid.jpaDAO.CarrierRepository;
import com.example.rfid.jpaDAO.ChemicalRepository;
import com.example.rfid.service.RfidService;
import com.example.rfid.service.UUIDService;
import com.example.rfid.utils.rfid.NoRfidReadException;
import com.example.rfid.utils.rfid.RfidDeviceUtil;
import com.example.rfid.websocket.UUIDServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Slf4j
@Service
public class RfidServiceImpl implements RfidService {
	@Resource
	InOutLogDao inOutLogDao;

	@Resource
	LabelDao labelDao;

	@Autowired
	UUIDServer uuidServer;
	@Autowired
	ChemicalRepository chemicalRepository;
	@Autowired
	CarrierRepository carrierRepository;

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
		String dataEPC = RfidDeviceUtil.readEPC();
		RfidDeviceUtil.resetReadEPC();
		System.out.println("dataUSER: "+dataUSER);
		System.out.println("dataEPC: "+dataEPC);
		return dataUSER;
	}

	@Override
	public int readChemicalId() throws NoRfidReadException {
		String portName = "COM7";
		RfidDeviceUtil.setConnector(portName, 115200);
		String dataEPC = RfidDeviceUtil.readEPC();
		RfidDeviceUtil.resetReadEPC();

		String[] dataArr = dataEPC.trim().split(" ");
		if(dataArr.length==6){
			throw new NoRfidReadException();
		}
		StringBuffer result = new StringBuffer();
		for(int i = 9;i<19;i++){
			result.append((Integer.parseInt(dataArr[i])-30));
		}
		System.out.println("chemicalId: "+Integer.parseInt(result.toString()));
//        Chemical chemical = chemicalRepository.findChemicalByChemicalID( Integer.parseInt(result.toString()));
//		uuidServer.sendInfo(chemical.getChemicalUUID());
		return Integer.parseInt(result.toString());
	}

	@Override
	public int readCarrierId() throws NoRfidReadException {
		String portName = "COM7";
		RfidDeviceUtil.setConnector(portName, 115200);
		String dataEPC = RfidDeviceUtil.readEPC();
		RfidDeviceUtil.resetReadEPC();

		String[] dataArr = dataEPC.trim().split(" ");
		if(dataArr.length==6){
			throw new NoRfidReadException();
		}
		StringBuffer result = new StringBuffer();
		for(int i = 9;i<19;i++){
			result.append((Integer.parseInt(dataArr[i])-30));
		}
		System.out.println("carrierID: "+Integer.parseInt(result.toString()));
//		Carrier carrier =carrierRepository.findCarrierByCarrierID ( Integer.parseInt(result.toString()));
//		uuidServer.sendInfo(carrier.getCarrierUUID());
		return Integer.parseInt(result.toString());
	}

	@Override
	public boolean writeChemicalIdNew(String chemicalId) throws NoRfidReadException {
		readChemicalId();
		System.out.println(chemicalId);
		String portName = "COM7";
		byte[] bytes = chemicalId.getBytes();
		RfidDeviceUtil.setConnector(portName, 115200);
		boolean success = RfidDeviceUtil.writeEPC(fill_id(chemicalId), 10);
		return success==true;
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
		resetEmptyEPC();
		resetEmptyUSER();
		// TODO 写一个update 方法

			//labelDao.update(new Label(Integer.parseInt(label_id),0,0));
		labelDao.deleteById(Integer.parseInt(label_id));
		labelDao.insert(new Label(Integer.parseInt(label_id),0,0));
		return null;
	}

	@Override
	public String resetEmptyEPC(){
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		//boolean success1 = RfidDeviceUtil.resetUser();
		boolean success2 = RfidDeviceUtil.resetEPC();

		return success2==true ? "1" : "0";
	}

	@Override
	public String resetEmptyUSER(){
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		boolean success1 = RfidDeviceUtil.resetUser();
		//boolean success2 = RfidDeviceUtil.resetEPC();

		return success1==true ? "1" : "0";
	}

	@Override
	public String writeChemicalID(String label_id,String chemicalId){
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);

		boolean success = RfidDeviceUtil.writeEPC(fill_id(chemicalId), 10);

		if(success){
			labelDao.insert(new Label(Integer.parseInt(label_id),0,Integer.parseInt(chemicalId)));
		}

		return success==true ? "1" : "0";
	}

	@Override
	public String writeChemicalID(String chemicalId) {
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);

		boolean success = RfidDeviceUtil.writeUSER(chemicalId, 10);
		log.info(success?"fail to ":"succeed to "+"write id:"+chemicalId+" to rfid.");
//		if(success){
//			labelDao.insert(new Label(0,0,Integer.parseInt(chemicalId)));
//		}

		return success==true ? "1" : "0";
	}

	@Override
	public String writeInboundInfo(String label_id,String chemicalId){
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		String result = "1";
		boolean success = RfidDeviceUtil.writeUSER(result, 1);


		return success==true ? "1" : "0";
	}

	@Override
	public String writeOutboundInfo(String label_id, String chemicalId) {
		String portName = "/dev/"+findTty();
		RfidDeviceUtil.setConnector(portName, 115200);
		String result = "0";
		boolean success = RfidDeviceUtil.writeUSER(result, 1);


		return success==true ? "1" : "0";
	}

    public static String fill_id(String id){
        if(id.length()==10){
            return id;
        }
        while(id.length()!=10){
            id = "0"+id;
        }
        return id;
    }

}

