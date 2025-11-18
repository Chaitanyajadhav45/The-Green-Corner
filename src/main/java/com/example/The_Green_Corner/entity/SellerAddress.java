package com.example.The_Green_Corner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seller_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;
    private String city;
    private String state;
    private String country;
    private String pincode;
}
