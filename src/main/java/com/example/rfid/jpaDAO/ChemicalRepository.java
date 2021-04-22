package com.example.rfid.jpaDAO;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.rfid.entity.Chemical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemicalRepository extends JpaRepository<Chemical,Integer> {
    Chemical findChemicalByChemicalID(int id);
}
