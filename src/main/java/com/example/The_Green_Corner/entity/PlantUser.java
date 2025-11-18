package com.example.The_Green_Corner.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plantuser")
public class PlantUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
    private long contact;
		
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    @JsonManagedReference
    private Cart cart;


	  
}
