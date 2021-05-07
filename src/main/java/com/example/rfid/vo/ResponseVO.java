package com.example.rfid.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO<T> {

    private Boolean success;

    private String message;

    private T content;

    public static <T> ResponseVO<T> buildSuccess() {
        return new ResponseVO<T>(true,"success");
    }

    public static <T> ResponseVO<T> buildSuccess(T content) {
        return new ResponseVO<T>(true,"success",content);

    }

    public static <T> ResponseVO buildFailure(String message) {
        return new ResponseVO(false,message);
    }

    public ResponseVO(boolean status, String mes){
        this.success=status;
        this.message=mes;
    }
}

