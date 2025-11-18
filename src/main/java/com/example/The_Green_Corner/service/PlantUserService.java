package com.example.The_Green_Corner.service;

import com.example.The_Green_Corner.entity.PlantUser;
import com.example.The_Green_Corner.repository.PlantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantUserService {

    @Autowired
    private PlantUserRepository repo;

    // Signup
    public PlantUser signup(PlantUser user) {
        return repo.save(user);
    }

    // Signin
    public PlantUser signin(String email, String password) {
        PlantUser user = repo.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    // CRUD
    public List<PlantUser> getAllUsers() {
        return repo.findAll();
    }

    public Optional<PlantUser> getUserById(long id) {
        return repo.findById(id);
    }

    public PlantUser updateUser(long id, PlantUser updatedUser) {
        PlantUser existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existing.setUsername(updatedUser.getUsername());
        existing.setEmail(updatedUser.getEmail());
        existing.setContact(updatedUser.getContact());
        existing.setPassword(updatedUser.getPassword());
        return repo.save(existing);
    }

    public void deleteUser(long id) {
        repo.deleteById(id);
    }
}
