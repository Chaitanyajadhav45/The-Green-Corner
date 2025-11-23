package com.jsp.theGreenCorner.Entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

/**
 * Cart entity
 * Relationships:
 *  - ManyToMany with Plant
 *  - ManyToMany with Equipment
 */
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Plant> plant;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Equipment> equip;
}
