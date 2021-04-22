package com.example.rfid.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.rfid.entity.Carrier;
import com.example.rfid.entity.Chemical;
import com.example.rfid.jpaDAO.CarrierRepository;
import com.example.rfid.jpaDAO.ChemicalRepository;
import com.example.rfid.service.UUIDService;
import com.example.rfid.websocket.UUIDServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DS("slave_1")
public class UUIDServiceImpl implements UUIDService {
    @Autowired
    ChemicalRepository chemicalRepository;
    @Autowired
    CarrierRepository carrierRepository;
    @Autowired
    UUIDServer uuidServer;

    @Override
    public void sendChemicalUUID(int id) {
        Chemical chemical = chemicalRepository.findChemicalByChemicalID(id);
        if(chemical==null){
            return;
        }
        String uuid = chemical.getChemicalUUID();
        uuidServer.sendInfo(uuid);
    }

    @Override
    public void sendCarrierUUID(int id) {
        Carrier carrier = carrierRepository.findCarrierByCarrierID(id);
        if(carrier==null){
            return;
        }
        String uuid = carrier.getCarrierUUID();
        uuidServer.sendInfo(uuid);
    }
}
