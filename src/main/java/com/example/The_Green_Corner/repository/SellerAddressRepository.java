package com.example.The_Green_Corner.repository;

import com.example.The_Green_Corner.entity.SellerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerAddressRepository extends JpaRepository<SellerAddress, Long> {
}
