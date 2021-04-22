package com.example.rfid.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`chemical`")
@EntityListeners(AuditingEntityListener.class)
public class Chemical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chemical_id")
    private int chemicalID;
    @Column(name = "chemical_uuid")
    private String chemicalUUID;
    @Column(name = "chemical_name")
    private String chemicalName;
    @Column(name = "cas_uuid")
    private String casUUID;
    @Column(name = "carrier_uuid")
    private String carrierUUID;
    @Column(name = "in_park")
    private int inPark;
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    public Chemical(String chemicalUUID, String chemicalName, String casUUID, String carrierUUID) {
        this.chemicalUUID = chemicalUUID;
        this.chemicalName = chemicalName;
        this.casUUID = casUUID;
        this.carrierUUID = carrierUUID;
    }
}
