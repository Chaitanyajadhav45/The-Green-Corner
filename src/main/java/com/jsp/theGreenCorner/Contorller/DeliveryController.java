package com.jsp.theGreenCorner.Contorller;

import com.jsp.theGreenCorner.Entity.Delivery;
import com.jsp.theGreenCorner.Services.DeliveryServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * POST:   http://localhost:8080/delivery/save/{userId}
 * GET:    http://localhost:8080/delivery
 * GET:    http://localhost:8080/delivery/{id}
 * DELETE: http://localhost:8080/delivery/{id}
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryServices dser;

    public DeliveryController(DeliveryServices dser) {
        this.dser = dser;
    }

    @PostMapping("/save/{userId}")
    public ResponseEntity<Delivery> saveDelivery(
            @PathVariable long userId,
            @RequestBody Delivery d) {

        return ResponseEntity.ok(dser.saveDelivery(userId, d));
    }

    @GetMapping
    public ResponseEntity<List<Delivery>> getAll() {
        return ResponseEntity.ok(dser.getAllDeliveries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getById(@PathVariable long id) {
        return ResponseEntity.ok(dser.getDeliveryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(dser.deleteDelivery(id));
    }
}
