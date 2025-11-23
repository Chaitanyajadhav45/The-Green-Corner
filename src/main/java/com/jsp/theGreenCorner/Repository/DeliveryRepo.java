package com.jsp.theGreenCorner.Repository;

import com.jsp.theGreenCorner.Entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery, Long> {

}
