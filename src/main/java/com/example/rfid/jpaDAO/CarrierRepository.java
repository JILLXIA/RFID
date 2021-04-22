package com.example.rfid.jpaDAO;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.rfid.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier,Integer> {
    Carrier findCarrierByCarrierID(int id);
}
