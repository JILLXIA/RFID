package com.example.rfid.service.impl;

import com.example.rfid.dao.CommonDao;
import com.example.rfid.entity.Init;
import com.example.rfid.rfidHelper.RFIDReaderHelper;
import com.example.rfid.rfidHelper.ReaderConnector;
import com.example.rfid.rfidHelper.rxobserver.RXObserver;
import com.example.rfid.rfidHelper.rxobserver.bean.RXInventoryTag;
import com.example.rfid.rfidInteraction.RXTXListener;
import com.example.rfid.rfidInteraction.ReaderHelper;
import com.example.rfid.rfidUtil.StringTool;
import com.example.rfid.service.RfidReadService;
import com.example.rfid.vo.InitVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

@Service
public class RfidReadServiceImp implements RfidReadService {
	Init init = new Init();

	@Resource
	CommonDao commonDao;

	ReaderHelper mReaderHelper;

	String send_data = "";
	String receive_data = "";

	Observer mObserver = new RXObserver() {
		@Override
		protected void onInventoryTag(RXInventoryTag tag) {
			System.out.println("EPC data:" + tag.strEPC);
		}

		@Override
		protected void onInventoryTagEnd(RXInventoryTag.RXInventoryTagEnd endTag) {
			System.out.println("inventory end:" + endTag.mTotalRead);
			((RFIDReaderHelper) mReaderHelper).realTimeInventory((byte) 0xff, (byte) 0x01);
		}

		@Override
		protected void onExeCMDStatus(byte cmd, byte status) {
			System.out.format("CDM:%s  Execute status:%S",
					String.format("%02X", cmd), String.format("%02X", status));
		}

	};
	int flag = 0;
	RXTXListener mListener = new RXTXListener() {
		@Override
		public void reciveData(byte[] btAryReceiveData) {
			// TODO Auto-generated method stub
			//receiveData调用了两次，第一次传的是A0，第二次传的是后面的数据
			receive_data = receive_data+" "+StringTool.byteArrayToString(btAryReceiveData, 0, btAryReceiveData.length);
			System.out.println("reciveData " + receive_data);
			flag++;
			if(flag == 2){
				init.setReceiveRfid_id(receive_data);
				commonDao.insert(init);
				flag = 0;
			}
		}

		@Override
		public void sendData(byte[] btArySendData) {
			// TODO Auto-generated method stub
			send_data = StringTool.byteArrayToString(btArySendData, 0, btArySendData.length);
			System.out.println("sendData" + send_data);
			init.setSendRfid_id(send_data);
		}

		@Override
		public void onLostConnect() {
			// TODO Auto-generated method stub
		}

	};

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
	public void getRfidId() {
		final ReaderConnector mConnector = new ReaderConnector();
		String portName = "/dev/" + findTty();
		System.out.println(portName);
		mReaderHelper = mConnector.connectCom(portName, 115200);
		if (mReaderHelper != null) {
			System.out.println("Connect success!");
			try {
				mReaderHelper.registerObserver(mObserver);
				mReaderHelper.setRXTXListener(mListener);
				byte[] passwd = new byte[]{0, 0, 0, 0};
				while (true) {
					((RFIDReaderHelper) mReaderHelper).readTag((byte) 1, (byte) 3, (byte) 0, (byte) 8, passwd);
					TimeUnit.SECONDS.sleep(1);
					send_data = "";
					receive_data = "";
				}

//				((RFIDReaderHelper) mReaderHelper).readTag((byte) 1, (byte) 3, (byte) 0, (byte) 8, passwd);
//				init.setSendRfid_id(send_data);
//				init.setReceiveRfid_id(receive_data);
//				System.out.println(init.getReceiveRfid_id());
//				System.out.println(init.getSendRfid_id());
//				this.commonDao.insert(init);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Connect faild!");
			mConnector.disConnect();
		}
		//return new InitVO();
	}
}
