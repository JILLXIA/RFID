package com.example.rfid.websocket;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.rfid.encoder.StringEncoder;
import com.example.rfid.jpaDAO.CarrierRepository;
import com.example.rfid.jpaDAO.ChemicalRepository;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
//,encoders = { AnnouncementListEncoder.class }


@ServerEndpoint(value = "/websocket/uuid",encoders = {StringEncoder.class})
@Component
public class UUIDServer {




    @Autowired
    public void setChemicalRepository(ChemicalRepository chemicalRepository){
        UUIDServer.chemicalRepository = chemicalRepository;
    }
    @Autowired
    public void setCarrierRepository(CarrierRepository carrierRepository){
        UUIDServer.carrierRepository = carrierRepository;
    }
    private static ChemicalRepository chemicalRepository;

    private static CarrierRepository carrierRepository;


    private static AtomicInteger onlineNum = new AtomicInteger();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    //发送消息

    public void sendMessage(Session session, Object message) throws IOException, EncodeException {
        if(session != null){

            synchronized (session) {
//                System.out.println("发送数据：" + message);

                session.getBasicRemote().sendObject(message);
            }
        }
    }
    
    //给所有用户发送信息
    public void sendInfo(String message){

        for (Session session: sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }


    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session){
        sessionPools.put(session.getId(),session);
        addOnlineCount();
        System.out.println(session.getId() + "加入webSocket！当前人数为" + onlineNum);
//        announcementRepository = applicationContext.getBean(announcementRepository.getClass());
//        announcementReadRepository = applicationContext.getBean(announcementReadRepository.getClass());

    }

    //关闭连接时调用
    @OnClose
    public void onClose(Session session){
        sessionPools.remove(session.getId());
        subOnlineCount();
        System.out.println(session.getId() + "断开webSocket连接！当前人数为" + onlineNum);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException{
        message = "客户端：" + message + ",已收到";
        System.out.println(message);
        for (Session session: sessionPools.values()) {
            try {
                sendMessage(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("发生错误");
        throwable.printStackTrace();
    }

    public static void addOnlineCount(){
        onlineNum.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineNum.decrementAndGet();
    }
}
