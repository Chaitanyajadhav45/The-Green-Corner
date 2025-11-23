package com.jsp.theGreenCorner.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.theGreenCorner.Entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{

}
