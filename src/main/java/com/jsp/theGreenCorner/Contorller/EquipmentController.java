package com.jsp.theGreenCorner.Contorller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jsp.theGreenCorner.Entity.Equipment;
import com.jsp.theGreenCorner.Entity.Review;
import com.jsp.theGreenCorner.Services.EquipmentServices;

/**
 * Equipment endpoints:
 * POST   http://localhost:8080/equip/save
 * GET    http://localhost:8080/equip
 * GET    http://localhost:8080/equip/{id}
 * PUT    http://localhost:8080/equip/{id}       -> update equipment
 * DELETE http://localhost:8080/equip/{id}
 * PUT    http://localhost:8080/equip/{EquipId}/review -> add review
 */
@RestController
@RequestMapping("/equip")
public class EquipmentController {

    private final EquipmentServices eser;

    public EquipmentController(EquipmentServices eser) {
        super();
        this.eser = eser;
    }

    @PostMapping("/save")
    public ResponseEntity<Equipment> saveEquip(@RequestBody Equipment e) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eser.saveEquipment(e));
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquip() {
        return ResponseEntity.status(HttpStatus.FOUND).body(eser.getAllEquip());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getById(@PathVariable long id) {
        Equipment e = eser.findById(id);
        if (e == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable long id, @RequestBody Equipment updated) {
        Equipment e = eser.updateEquipment(id, updated);
        if (e == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(eser.deleteEquipment(id));
    }

    @PutMapping("/{EquipId}/review")
    public ResponseEntity<Equipment> addReviewEquip(@PathVariable long EquipId, @RequestBody Review re) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(eser.addReview(EquipId, re));
    }
}
