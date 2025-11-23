package com.jsp.theGreenCorner.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.theGreenCorner.Entity.Equipment;

@Repository
public interface EquipmentRepo  extends JpaRepository<Equipment, Long>{
 
}
