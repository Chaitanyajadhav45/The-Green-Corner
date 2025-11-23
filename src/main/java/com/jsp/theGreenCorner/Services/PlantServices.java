package com.jsp.theGreenCorner.Services;

import com.jsp.theGreenCorner.Entity.Plant;
import com.jsp.theGreenCorner.Entity.Review;
import com.jsp.theGreenCorner.Repository.PlantRepository;
import com.jsp.theGreenCorner.Repository.ReviewRepo;
import com.jsp.theGreenCorner.Repository.SellerAddRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantServices {
    @Autowired
    private final PlantRepository prepo;
    private final SellerAddRepo srepo;
    private final ReviewRepo rrepo;

    public PlantServices(PlantRepository prepo, SellerAddRepo srepo, ReviewRepo rrepo) {
        this.prepo = prepo;
        this.srepo = srepo;
        this.rrepo = rrepo;
    }

    // Create
    public Plant savePlant(Plant p) {
        return prepo.save(p);
    }

    // Read all
    public List<Plant> findAllPlant() {
        return prepo.findAll();
    }

    // Read by Id
    public Plant findPlantById(long id) {
        Optional<Plant> find = prepo.findById(id);
        return find.orElse(null);
    }

    // Update
    public Plant updatePlant(long id, Plant updated) {
        Optional<Plant> opt = prepo.findById(id);
        if (opt.isPresent()) {
            Plant exist = opt.get();
            exist.setName(updated.getName());
            exist.setDescription(updated.getDescription());
            exist.setPrice(updated.getPrice());
            exist.setDiscountPrice(updated.getDiscountPrice());
            exist.setRating(updated.getRating());
            exist.setTotalSalesLastMonth(updated.getTotalSalesLastMonth());
            exist.setSellerName(updated.getSellerName());
            exist.setSellerAddress(updated.getSellerAddress());
            exist.setAvailability(updated.getAvailability());
            exist.setQuantityAvailable(updated.getQuantityAvailable());
            exist.setCategories(updated.getCategories());
            exist.setSunlightRequirement(updated.getSunlightRequirement());
            exist.setMoistureRequirement(updated.getMoistureRequirement());
            exist.setSoilType(updated.getSoilType());
            exist.setSeason(updated.getSeason());
            exist.setGrowthRate(updated.getGrowthRate());
            exist.setPotSizeRequired(updated.getPotSizeRequired());
            exist.setGenus(updated.getGenus());
            exist.setLocalName(updated.getLocalName());
            exist.setRegionalName(updated.getRegionalName());
            exist.setBiologicalName(updated.getBiologicalName());
            exist.setBotanicalName(updated.getBotanicalName());
            exist.setTags(updated.getTags());
            exist.setShippingStatus(updated.getShippingStatus());
            exist.setPrimaryImage(updated.getPrimaryImage());
            exist.setSecondaryImages(updated.getSecondaryImages());
            exist.setShoppingPolicy(updated.getShoppingPolicy());
            exist.setRefundPolicy(updated.getRefundPolicy());
            return prepo.save(exist);
        }
        return null;
    }

    // Delete
    public String deletePlant(long id) {
        if (prepo.existsById(id)) {
            prepo.deleteById(id);
            return "Plant deleted successfully.";
        }
        return "Plant not found.";
    }

    // existing methods
    public Plant addReview(long id, Review re) {
        Optional<Plant> p = prepo.findById(id);
        if (p.isPresent()) {
            Plant found = p.get();
            found.getReviews().add(re);
            return prepo.save(found);
        }
        return null;
    }

    public List<Plant> findPlantByRating(double rating) {
        if (rating < 5.0) {
            List<Plant> found = prepo.findByRating(rating, (rating + 0.9));
            return found;
        } else {
            List<Plant> found = prepo.findByRating5(rating);
            return found;
        }
    }

    public Page<Plant> getPlants(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return prepo.findAll(pageable);
    }
}
