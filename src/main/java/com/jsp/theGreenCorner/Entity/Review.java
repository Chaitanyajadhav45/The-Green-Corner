package com.jsp.theGreenCorner.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;
import java.time.LocalDateTime;

/**
 * Review entity
 */
@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private int rating;
    private String comment;
    private boolean productDelivered;

    @CurrentTimestamp
    private LocalDateTime dateTime;

    private int likes;
    private int dislikes;
}
