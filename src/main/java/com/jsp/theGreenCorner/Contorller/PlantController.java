package com.jsp.theGreenCorner.Contorller;

import com.jsp.theGreenCorner.Entity.Cart;
import com.jsp.theGreenCorner.Entity.Plant;
import com.jsp.theGreenCorner.Entity.Review;
import com.jsp.theGreenCorner.Services.CartServices;
import com.jsp.theGreenCorner.Services.PlantServices;
import com.jsp.theGreenCorner.Services.ReviewServices;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Plant endpoints:
 * POST   http://localhost:8080/plant/save
 * GET    http://localhost:8080/plant/findAll
 * GET    http://localhost:8080/plant/{id}
 * PUT    http://localhost:8080/plant/{id}
 * DELETE http://localhost:8080/plant/{id}
 * PUT    http://localhost:8080/plant/{plantId}/review
 * PUT    http://localhost:8080/plant/addToCart/{plantId}/user/{uId}
 * PUT    http://localhost:8080/plant/addToCart/{equipId}/user/{uId}
 * GET    http://localhost:8080/plant/{rating}    (find by rating)
 * GET    http://localhost:8080/plant/page?page=0&size=6  (pagination)
 */
@RestController
@RequestMapping("/plant")
public class PlantController {

    private final PlantServices pser;
    private final ReviewServices rser;
    private final CartServices cser;

    public PlantController(PlantServices pser, ReviewServices rser, CartServices cser) {
        super();
        this.pser = pser;
        this.rser = rser;
        this.cser = cser;
    }

    @PostMapping("/saveReview")
    public ResponseEntity<Review> saveReview(@RequestBody Review p) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rser.saveReview(p));
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(rser.getAll());
    }

    @PostMapping("/save")
    public ResponseEntity<Plant> savePlant(@RequestBody Plant p) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pser.savePlant(p));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Plant>> finadAllP() {
        return ResponseEntity.status(HttpStatus.FOUND).body(pser.findAllPlant());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getById(@PathVariable long id) {
        Plant p = pser.findPlantById(id);
        if (p == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> update(@PathVariable long id, @RequestBody Plant updated) {
        Plant p = pser.updatePlant(id, updated);
        if (p == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(pser.deletePlant(id));
    }

    @PutMapping("/{plantId}/review")
    public ResponseEntity<Plant> addReviewPlant(@PathVariable long plantId, @RequestBody Review re) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pser.addReview(plantId, re));
    }

    @PutMapping("/addToCart/{plantId}/user/{uId}")
    public ResponseEntity<Cart> addToCart(@PathVariable long plantId, @PathVariable long uId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cser.addToCart(plantId, uId));
    }

    @PutMapping("/addToCart/{equipId}/user/{uId}")
    public ResponseEntity<Cart> addToCartEquip(@PathVariable long equipId, @PathVariable long uId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cser.addToCartEquip(equipId, uId));
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Plant>> findByRating(@PathVariable double rating) {
        return ResponseEntity.status(HttpStatus.FOUND).body(pser.findPlantByRating(rating));
    }

    @GetMapping("/page")
    public Page<Plant> getAllPlants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        return pser.getPlants(page, size);
    }
}
