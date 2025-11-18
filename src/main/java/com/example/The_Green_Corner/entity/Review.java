package com.example.The_Green_Corner.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private float rating;

    @Column(length = 1000)
    private String comment;

    private boolean productDelivered;
    private String dateTime;
    private int likes;
    private int dislikes;	
}
