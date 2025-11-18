package com.example.The_Green_Corner.controller;

import com.example.The_Green_Corner.entity.Cart;
import com.example.The_Green_Corner.entity.Plant;
import com.example.The_Green_Corner.entity.Review;
import com.example.The_Green_Corner.entity.SellerAddress;
import com.example.The_Green_Corner.service.CartService;
import com.example.The_Green_Corner.service.PlantService;
import com.example.The_Green_Corner.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService service;

    @Autowired
    private ReviewService reviewService;
    
    @Autowired
    private CartService cartService ;

    //  POST /plant/save
    @PostMapping("/save")
    public ResponseEntity<Plant> savePlant(@RequestBody Plant plant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePlant(plant));
    }

    //  GET /plant/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable long id) {
        return service.getPlantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  GET /plant/findAll
    @GetMapping("/findAll")
    public ResponseEntity<List<Plant>> findAllPlants() {
        return ResponseEntity.ok(service.getAllPlants());
    }

    //  PUT /plant/update
    @PutMapping("/update")
    public ResponseEntity<Plant> updatePlant(@RequestBody Plant plant) {
        return ResponseEntity.ok(service.updatePlant(plant));
    }

    //  DELETE /plant/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable long id) {
        service.deletePlant(id);
        return ResponseEntity.ok("Plant deleted successfully.");
    }

    //  POST /plant/{plantId}/address
    @PostMapping("/{plantId}/address")
    public ResponseEntity<Plant> addAddressToPlant(
            @PathVariable long plantId,
            @RequestBody SellerAddress address) {

        Plant plant = service.getPlantById(plantId).orElse(null);
        if (plant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        plant.setSellerAddress(address);
        Plant updatedPlant = service.updatePlant(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPlant);
    }

    @PostMapping("/{plantId}/review")
    public ResponseEntity<Plant> addReviewToPlant(
            @PathVariable long plantId,
            @RequestBody Review review) {

        Plant plant = service.getPlantById(plantId).orElse(null);
        if (plant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        reviewService.saveReview(review);

        List<Review> reviews = plant.getReviews();
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
        plant.setReviews(reviews);

        Plant updatedPlant = service.updatePlant(plant);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedPlant);
    }
}