package com.example.rfid.rfidHelper;

import com.example.rfid.rfidInteraction.DataPackageProcess;
import com.example.rfid.rfidHelper.bean.MessageTran;

/**
 * The implementation class of DataPackageProcess.
 */

public class ReaderDataPackageProcess extends DataPackageProcess {
    @Override
    public void analyData(byte[] btPackage) {
        MessageTran msgTran = new MessageTran(btPackage);
        if (msgTran != null) {
            setChanged();
            notifyObservers(msgTran);
        }
    }
}
