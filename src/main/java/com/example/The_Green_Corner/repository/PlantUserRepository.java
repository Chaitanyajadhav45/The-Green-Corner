package com.example.The_Green_Corner.repository;

import com.example.The_Green_Corner.entity.PlantUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantUserRepository extends JpaRepository<PlantUser, Long> {
	Optional<PlantUser> findByEmail(String email);
}
