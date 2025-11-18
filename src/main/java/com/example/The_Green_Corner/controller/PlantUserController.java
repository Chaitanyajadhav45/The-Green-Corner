package com.example.The_Green_Corner.controller;

import com.example.The_Green_Corner.entity.Cart;
import com.example.The_Green_Corner.entity.Plant;
import com.example.The_Green_Corner.entity.PlantUser;
import com.example.The_Green_Corner.service.CartService;
import com.example.The_Green_Corner.service.PlantService;
import com.example.The_Green_Corner.service.PlantUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class PlantUserController {

	@Autowired
	private PlantService plantService;
	
	@Autowired
	private CartService cartService;
	
    @Autowired
    private PlantUserService service;

    // user Sign up 
    @PostMapping("/signup")
    public ResponseEntity<PlantUser> signup(@RequestBody PlantUser user) {
    	Cart cart = new Cart();
    	user.setCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.signup(user));
    }

    // user Sign in
    @PostMapping("/signin")
    public ResponseEntity<PlantUser> signin(@RequestBody PlantUser user) {

        PlantUser loggedInUser = service.signin(user.getEmail(), user.getPassword());

        if (loggedInUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(loggedInUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<PlantUser>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlantUser> getUserById(@PathVariable long id) {
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<PlantUser> updateUser(@PathVariable long id, @RequestBody PlantUser user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        service.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    
    @PostMapping("/addToCart/{plantId}/{userId}")
    public ResponseEntity<Cart> addPlantToCart(@PathVariable long plantId, @PathVariable long userId) {
    	 PlantUser user = service.getUserById(userId).orElse(null);
    	    if (user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    	    Plant plant = plantService.getPlantById(plantId).orElse(null);
    	    if (plant == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    	    Cart cart = user.getCart();
    	    if (cart.getPlant() == null) {
    	        cart.setPlant(new ArrayList<>());
    	    }

    	    cart.getPlant().add(plant);
    	    Cart updatedCart = cartService.saveCart(cart);

    	    return ResponseEntity.ok(updatedCart);
    	

    }
    
}
