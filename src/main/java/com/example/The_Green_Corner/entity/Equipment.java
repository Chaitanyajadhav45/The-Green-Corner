package com.example.The_Green_Corner.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	
	    private String name;
	    private String description;
	    private double price;
	    private double rating;
	    private String availability;
	    private int quantityAvailable;
	    private String shippingPolicy;
	    private String refundPolicy;
	    private String primaryImage;
	    
	    @OneToMany(cascade = CascadeType.ALL)
	    private List<Review> review;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    private Seller seller;
	    
	    @ElementCollection
	    private List<String> categories;
	    
	    @ElementCollection
	    private List<String> tags;
	    
	    @ElementCollection
	    private List<String> secondaryImages;

}
