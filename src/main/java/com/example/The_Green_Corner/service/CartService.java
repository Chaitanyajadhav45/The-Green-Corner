package com.example.The_Green_Corner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.The_Green_Corner.entity.Cart;
import com.example.The_Green_Corner.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepo ;
	
	public Cart saveCart(Cart cart) {
		return cartRepo.save(cart);
	}
	
	public List<Cart> findAll(){
		return cartRepo.findAll();
	}
	
	public Optional<Cart> findCartById(long id){
		return cartRepo.findById(id) ;
	}

	
}
