package com.jsp.theGreenCorner.Entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 * Equipment entity
 */
@Data
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ElementCollection
    private List<String> categories;

    @OneToOne(cascade = CascadeType.ALL)
    private Seller seller;

    private String availability;
    private long quantityAvailable;

    @ElementCollection
    private List<String> tags;

    private String shippingPolicy;
    private String refundPolicy;
    private String primaryImage;

    @ElementCollection
    private List<String> secondaryImages;
}
