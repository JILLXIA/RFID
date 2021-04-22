package com.example.rfid.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "`carrier`")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrier_id")
    private int carrierID;
    @Column(name = "carrier_uuid")
    private String carrierUUID;
    @Column(name = "carrier_name")
    private String carrierName;
    @Column(name = "carrier_driver")
    private String carrierDriver;
    @Column(name = "carrier_number_plate")
    private String carrierNumberPlate;
    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;

    public Carrier(String carrierUUID, String carrierDriver, String carrierName, String carrierNumberPlate) {
        this.carrierUUID = carrierUUID;
        this.carrierDriver = carrierDriver;
        this.carrierName = carrierName;
        this.carrierNumberPlate = carrierNumberPlate;
    }
}
