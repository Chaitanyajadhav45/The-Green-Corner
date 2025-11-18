package com.example.The_Green_Corner.controller;

import com.example.The_Green_Corner.entity.SellerAddress;
import com.example.The_Green_Corner.service.SellerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class SellerAddressController {

    @Autowired
    private SellerAddressService service;

    @PostMapping("/add")
    public ResponseEntity<SellerAddress> addAddress(@RequestBody SellerAddress address) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAddress(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerAddress> getAddressById(@PathVariable long id) {
        return service.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<SellerAddress>> getAllAddresses() {
        return ResponseEntity.ok(service.getAllAddresses());
    }

    @PutMapping("/update")
    public ResponseEntity<SellerAddress> updateAddress(@RequestBody SellerAddress address) {
        return ResponseEntity.ok(service.updateAddress(address));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        service.deleteAddress(id);
        return ResponseEntity.ok("Seller address deleted successfully.");
    }
}
