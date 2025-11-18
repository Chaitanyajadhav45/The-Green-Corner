package com.example.The_Green_Corner.service;

import com.example.The_Green_Corner.entity.Review;
import com.example.The_Green_Corner.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repo;

    public Review saveReview(Review review) {
        return repo.save(review);
    }

    public List<Review> getAllReviews() {
        return repo.findAll();
    }

    public Optional<Review> getReviewById(long id) {
        return repo.findById(id);
    }

    public void deleteReview(long id) {
        repo.deleteById(id);
    }
}
