package com.example.rfid.encoder;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

@Component
public class StringEncoder implements Encoder.Text<String> {
    @Override
    public String encode(String str) throws EncodeException {
        return JSON.toJSONString(str);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}