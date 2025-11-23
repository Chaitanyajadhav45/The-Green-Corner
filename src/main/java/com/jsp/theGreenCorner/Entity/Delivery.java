package com.jsp.theGreenCorner.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String firstName;
    private String lastName;
    private String address;
    private String state;
    private String city;
    private long pincode;
    private String phone;
    private double totalAmount;

}
