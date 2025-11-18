package com.example.The_Green_Corner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.The_Green_Corner.entity.Cart;

public interface CartRepository extends  JpaRepository<Cart, Long> {

	
	
}
