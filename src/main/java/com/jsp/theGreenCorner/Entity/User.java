package com.jsp.theGreenCorner.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * User entity
 */
@Data
@Entity
@Table(name = "plantUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String userName;
    private String password;

    @Column(unique = true)
    private String email;
    private String contact ;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Delivery> deliveries = new ArrayList<>();

}
