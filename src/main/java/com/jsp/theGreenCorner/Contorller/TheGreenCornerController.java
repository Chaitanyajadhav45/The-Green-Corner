package com.jsp.theGreenCorner.Contorller;

import com.jsp.theGreenCorner.Entity.*;
import com.jsp.theGreenCorner.Services.*;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MASTER CONTROLLER: TheGreenCornerController

 * Base URL: http://localhost:8080/thegreencorner

 */

@RestController
@RequestMapping("/thegreencorner")
public class TheGreenCornerController {

    private final PlantServices pser;
    private final ReviewServices rser;
    private final CartServices cser;
    private final EquipmentServices eser;
    private final UserServices userSer;
    private final ContactUsServices contactSer;
    private final EmailServices emailSer;
    private final DeliveryServices dser;
    private final UPIService upiService;

    public TheGreenCornerController(
            PlantServices pser,
            ReviewServices rser,
            CartServices cser,
            EquipmentServices eser,
            UserServices userSer,
            ContactUsServices contactSer,
            EmailServices emailSer,
            DeliveryServices dser,
            UPIService upiService
    ) {
        this.pser = pser;
        this.rser = rser;
        this.cser = cser;
        this.eser = eser;
        this.userSer = userSer;
        this.contactSer = contactSer;
        this.emailSer = emailSer;
        this.dser = dser;
        this.upiService = upiService;
    }

    /* ==========================================
                   PLANT APIs
       ========================================== */

    @PostMapping("/plant/saveReview")
    public ResponseEntity<Review> saveReview(@RequestBody Review p) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rser.saveReview(p));
    }

    @GetMapping("/plant/review")
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.status(HttpStatus.FOUND).body(rser.getAll());
    }

    @PostMapping("/plant/save")
    public ResponseEntity<Plant> savePlant(@RequestBody Plant p) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pser.savePlant(p));
    }

    @GetMapping("/plant/findAll")
    public ResponseEntity<List<Plant>> findAllPlants() {
        return ResponseEntity.status(HttpStatus.FOUND).body(pser.findAllPlant());
    }

    @GetMapping("/plant/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable long id) {
        Plant p = pser.findPlantById(id);
        if (p == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(p);
    }

    @PutMapping("/plant/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable long id, @RequestBody Plant updated) {
        Plant p = pser.updatePlant(id, updated);
        if (p == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(p);
    }

    @DeleteMapping("/plant/{id}")
    public ResponseEntity<String> deletePlant(@PathVariable long id) {
        return ResponseEntity.ok(pser.deletePlant(id));
    }

    @PutMapping("/plant/{plantId}/review")
    public ResponseEntity<Plant> addReviewToPlant(@PathVariable long plantId, @RequestBody Review re) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pser.addReview(plantId, re));
    }

    @PutMapping("/plant/addToCart/{plantId}/user/{uId}")
    public ResponseEntity<Cart> addPlantToCart(@PathVariable long plantId, @PathVariable long uId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cser.addToCart(plantId, uId));
    }

    @GetMapping("/plant/rating/{rating}")
    public ResponseEntity<List<Plant>> findPlantsByRating(@PathVariable double rating) {
        return ResponseEntity.status(HttpStatus.FOUND).body(pser.findPlantByRating(rating));
    }

    @GetMapping("/plant/page")
    public Page<Plant> getAllPlantsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        return pser.getPlants(page, size);
    }

    /* ==========================================
                  EQUIPMENT APIs
       ========================================== */

    @PostMapping("/equip/save")
    public ResponseEntity<Equipment> saveEquipment(@RequestBody Equipment e) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eser.saveEquipment(e));
    }

    @GetMapping("/equip")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.status(HttpStatus.FOUND).body(eser.getAllEquip());
    }

    @PutMapping("/equip/{EquipId}/review")
    public ResponseEntity<Equipment> addReviewToEquip(@PathVariable long EquipId, @RequestBody Review re) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eser.addReview(EquipId, re));
    }

    /* ==========================================
                  USER APIs
       ========================================== */

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.FOUND).body(userSer.getAllUser());
    }

    @PostMapping("/user/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userSer.singup(user));
    }

    @PostMapping("/user/signin/{email}/{password}")
    public ResponseEntity<User> signin(@PathVariable String email, @PathVariable String password) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userSer.singin(email, password));
    }

    /* ==========================================
                CONTACT US APIs
       ========================================== */

    @PostMapping("/contact/save")
    public ResponseEntity<ContactUs> saveContact(@RequestBody ContactUs c) {
        ContactUs saved = contactSer.saveMessage(c);

        emailSer.sendEmail(
                c.getEmail(),
                c.getSubject(),
                "Hello " + c.getName() + ",\n\n" + c.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/contact")
    public ResponseEntity<List<ContactUs>> getAllContact() {
        return ResponseEntity.status(HttpStatus.FOUND).body(contactSer.getAllMessages());
    }

    /* ==========================================
                DELIVERY APIs
       ========================================== */

    @PostMapping("/delivery/save/{userId}")
    public ResponseEntity<Delivery> saveDelivery(
            @PathVariable long userId,
            @RequestBody Delivery d) {
        return ResponseEntity.ok(dser.saveDelivery(userId, d));
    }

    @GetMapping("/delivery")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(dser.getAllDeliveries());
    }

    /* ==========================================
                  CART APIs
       ========================================== */

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable long cartId) {
        return ResponseEntity.ok(cser.deleteCart(cartId));
    }
    @GetMapping("/cart/user/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable long userId) {
        Cart c = cser.getCartByUser(userId);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }
    @DeleteMapping("/cart/removePlant/{userId}/{plantId}")
    public ResponseEntity<Cart> removePlant(
            @PathVariable long userId,
            @PathVariable long plantId) {
        return ResponseEntity.ok(cser.removePlantFromCart(userId, plantId));
    }
    @DeleteMapping("/cart/removeEquip/{userId}/{equipId}")
    public ResponseEntity<Cart> removeEquip(
            @PathVariable long userId,
            @PathVariable long equipId) {
        return ResponseEntity.ok(cser.removeEquipFromCart(userId, equipId));
    }
    @DeleteMapping("/cart/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable long userId) {
        return ResponseEntity.ok(cser.clearCart(userId));
    }

    /* ==========================================
                  UPI APIs
       ========================================== */

    @GetMapping("/upi/generate")
    public ResponseEntity<String> generateUPILink(@RequestParam double amount) {
        return ResponseEntity.ok(upiService.generateUPILink(amount));
    }
}
