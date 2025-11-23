package com.jsp.theGreenCorner.Services;

import com.jsp.theGreenCorner.Entity.Review;
import com.jsp.theGreenCorner.Repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServices {

    @Autowired
    private final ReviewRepo repo;

    public ReviewServices(ReviewRepo repo) {
        this.repo = repo;
    }

    public Review saveReview(Review r) {
        return repo.save(r);
    }

    public List<Review> getAll() {
        return repo.findAll();
    }

    public Review findById(long id) {
        Optional<Review> opt = repo.findById(id);
        return opt.orElse(null);
    }

    public Review updateReview(long id, Review updated) {
        Optional<Review> opt = repo.findById(id);
        if (opt.isPresent()) {
            Review exist = opt.get();
            exist.setUsername(updated.getUsername());
            exist.setRating(updated.getRating());
            exist.setComment(updated.getComment());
            exist.setProductDelivered(updated.isProductDelivered());
            exist.setLikes(updated.getLikes());
            exist.setDislikes(updated.getDislikes());
            return repo.save(exist);
        }
        return null;
    }

    public String deleteReview(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Review deleted successfully.";
        }
        return "Review not found.";
    }
}
