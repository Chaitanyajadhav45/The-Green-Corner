package com.example.The_Green_Corner.service;

import com.example.The_Green_Corner.entity.SellerAddress;
import com.example.The_Green_Corner.repository.SellerAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerAddressService {

    @Autowired
    private SellerAddressRepository repo;

    public SellerAddress saveAddress(SellerAddress address) {
        return repo.save(address);
    }

    public Optional<SellerAddress> getAddressById(long id) {
        return repo.findById(id);
    }

    public List<SellerAddress> getAllAddresses() {
        return repo.findAll();
    }

    public SellerAddress updateAddress(SellerAddress address) {
        return repo.save(address);
    }

    public void deleteAddress(long id) {
        repo.deleteById(id);
    }
}
