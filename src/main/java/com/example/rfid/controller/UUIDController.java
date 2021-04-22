package com.example.rfid.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.rfid.jpaDAO.CarrierRepository;
import com.example.rfid.jpaDAO.ChemicalRepository;
import com.example.rfid.entity.Chemical;
import com.example.rfid.websocket.UUIDServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@DS("slave_1")
@RestController
public class UUIDController {
    @Autowired
    ChemicalRepository chemicalRepository;
    @Autowired
    CarrierRepository carrierRepository;
    @Autowired
    UUIDServer uuidServer;



    @RequestMapping(value = "/getChemicalUUID",method = RequestMethod.GET)
    public String getChemicalUUID(@RequestParam int id){
        uuidServer.sendInfo(chemicalRepository.findChemicalByChemicalID(id).getChemicalUUID());
        return null;
    }

    @RequestMapping(value = "/getCarrierUUID",method = RequestMethod.GET)
    public String getCarrierUUID(@RequestParam int id){
        return chemicalRepository.findChemicalByChemicalID(id).getChemicalUUID();
    }
}
