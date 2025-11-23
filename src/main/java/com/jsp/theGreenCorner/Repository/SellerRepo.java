package com.jsp.theGreenCorner.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.theGreenCorner.Entity.Seller;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Long> {

}
