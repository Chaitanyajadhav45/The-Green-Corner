package com.example.The_Green_Corner.controller;

import com.example.The_Green_Corner.entity.Review;
import com.example.The_Green_Corner.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping("/add")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveReview(review));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(service.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable long id) {
        return service.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable long id) {
        service.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully.");
    }
}
