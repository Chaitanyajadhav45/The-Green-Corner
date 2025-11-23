package com.jsp.theGreenCorner.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Plant entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private String description;
    private double price ;
    private double discountPrice;
    private double rating ;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;

    private long totalSalesLastMonth;
    private String sellerName;

    @OneToOne(cascade = CascadeType.ALL)
    private SellerAddress sellerAddress;

    private String availability;
    private int quantityAvailable ;

    @ElementCollection
    private List<String> categories;

    private String sunlightRequirement ;
    private String moistureRequirement;
    private String soilType;
    private String season;
    private String growthRate;
    private String potSizeRequired;
    private String genus;
    private String localName;
    private String regionalName;
    private String biologicalName;
    private String botanicalName;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private List<String> shippingStatus;

    private String primaryImage;

    @ElementCollection
    private List<String> secondaryImages;

    private String shoppingPolicy;
    private String refundPolicy;
}
