//package com.example.rfid.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class readService  implements ApplicationRunner {
//    @Autowired
//    RfidService rfidService;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        while(true){
//            try {
//                rfidService.readChemicalId();
//            }
//            catch (ArrayIndexOutOfBoundsException e){
//                System.out.println("未读到标签");
//            }
//        }
//
//    }
//}
