package com.jsp.theGreenCorner.Contorller;

import com.jsp.theGreenCorner.Entity.Review;
import com.jsp.theGreenCorner.Services.ReviewServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Review endpoints:
 * POST   /review/save
 * GET    /review
 * GET    /review/{id}
 * PUT    /review/{id}
 * DELETE /review/{id}
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewServices rser;

    public ReviewController(ReviewServices rser) {
        this.rser = rser;
    }

    @PostMapping("/save")
    public ResponseEntity<Review> save(@RequestBody Review r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rser.saveReview(r));
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAll() {
        return ResponseEntity.ok(rser.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getById(@PathVariable long id) {
        Review r = rser.findById(id);
        if (r == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable long id, @RequestBody Review updated) {
        Review r = rser.updateReview(id, updated);
        if (r == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(rser.deleteReview(id));
    }
}
