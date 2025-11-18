package com.example.The_Green_Corner.service;

import com.example.The_Green_Corner.entity.Plant;
import com.example.The_Green_Corner.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    @Autowired
    private PlantRepository repo;

    public Plant savePlant(Plant plant) {
        return repo.save(plant);
    }

    public Optional<Plant> getPlantById(long id) {
        return repo.findById(id);
    }

    public List<Plant> getAllPlants() {
        return repo.findAll();
    }

    public Plant updatePlant(Plant plant) {
        return repo.save(plant);
    }

    public void deletePlant(long id) {
        repo.deleteById(id);
    }
}
