package com.jsp.theGreenCorner.Repository;

import com.jsp.theGreenCorner.Entity.SellerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAddRepo extends JpaRepository<SellerAddress,Long> {
}
