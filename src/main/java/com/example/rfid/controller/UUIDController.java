package com.example.rfid.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.rfid.entity.Carrier;
import com.example.rfid.jpaDAO.CarrierRepository;
import com.example.rfid.jpaDAO.ChemicalRepository;
import com.example.rfid.entity.Chemical;
import com.example.rfid.service.RfidService;
import com.example.rfid.utils.rfid.NoRfidReadException;
import com.example.rfid.vo.ResponseVO;
import com.example.rfid.websocket.UUIDServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@DS("slave_1")
@RestController
public class UUIDController {
    @Autowired
    ChemicalRepository chemicalRepository;
    @Autowired
    CarrierRepository carrierRepository;
    @Autowired
    UUIDServer uuidServer;
    @Autowired
    RfidService rfidService;



    @RequestMapping(value = "/readChemicalUUIDFromRfid",method = RequestMethod.GET)
    public ResponseVO getChemicalUUID(){
        int id = 0;
        try{
            id =  rfidService.readChemicalId();
        }
        catch (NoRfidReadException e){
            System.err.println("Reading rfid failed");
            return ResponseVO.buildFailure("Reading rfid failed");
        }

        if(id!=0){
            Chemical chemical = chemicalRepository.findChemicalByChemicalID(id);
            if(chemical==null||chemical.getChemicalID()==0){
                return ResponseVO.buildFailure("Data error or Database access error.");
            }
            return ResponseVO.buildSuccess(chemical.getChemicalUUID());
        }
        else {
            return  ResponseVO.buildFailure("Reading rfid failed");
        }
    }

    @RequestMapping(value = "/writeChemicalIDToRfid",method = RequestMethod.GET)
    public ResponseVO writeChemicalIDToRfid(@RequestParam int id){
        try{
            boolean res = rfidService.writeChemicalIdNew(id+"");
            if(res){
                return ResponseVO.buildSuccess();
            }
            else {
                return ResponseVO.buildFailure("Writing rfid failed");
            }
        }
        catch (NoRfidReadException e){
            System.err.println("Reading rfid failed");
            return ResponseVO.buildFailure("Writing rfid failed");
        }


    }

    @RequestMapping(value = "/readCarrierUUIDFromRfid",method = RequestMethod.GET)
    public ResponseVO readCarrierUUIDFromRfid() {
        int id = 0;
        try{
            id =  rfidService.readCarrierId();
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("Reading rfid failed");
        }

        if(id!=0){
            Carrier carrier = carrierRepository.findCarrierByCarrierID(id);
            if(carrier==null||carrier.getCarrierID()==0){
                return ResponseVO.buildFailure("Data error or Database access error.");
            }
            return ResponseVO.buildSuccess(carrier.getCarrierUUID());
        }
        else {
            return  ResponseVO.buildFailure("Reading rfid failed");
        }


    }
}
