package com.example.The_Green_Corner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private double price;
    private double discountPrice;
    private float rating;
    private int totalSalesLastMonth;
    private String sellerName;
    private String availability;
    private int quantityAvailable;
    private String sunlightRequirement;
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
    @Column(length = 1000)
    private String primaryImage;

    @Column(length = 1000)
    private String shoppingPolicy;

    @Column(length = 1000)
    private String refundPolicy;

    @OneToOne
    private SellerAddress sellerAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews;
    
    @ElementCollection 	
    private List<String> categories ;
    
    @ElementCollection
    private List<String> tags ;
		
	@ElementCollection
	private List <String> shippingState ;
	
	@ElementCollection
	private List <String> secondaryImages ;

}
