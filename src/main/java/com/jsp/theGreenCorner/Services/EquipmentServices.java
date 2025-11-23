package com.jsp.theGreenCorner.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.theGreenCorner.Entity.Equipment;
import com.jsp.theGreenCorner.Entity.Review;
import com.jsp.theGreenCorner.Repository.EquipmentRepo;
import com.jsp.theGreenCorner.Repository.ReviewRepo;
import com.jsp.theGreenCorner.Repository.SellerRepo;

@Service
public class EquipmentServices {

    @Autowired
    private EquipmentRepo erepo;
    private ReviewRepo rrepo;
    private SellerRepo srepo;

    public EquipmentServices(EquipmentRepo erepo, ReviewRepo rrepo, SellerRepo srepo) {
        super();
        this.erepo = erepo;
        this.rrepo = rrepo;
        this.srepo = srepo;
    }

    // Save or create equipment
    public Equipment saveEquipment(Equipment e) {
        if (e.getReviews() != null) {
            for (Review re : e.getReviews()) {
                rrepo.save(re);
            }
        }
        if (e.getSeller() != null) {
            srepo.save(e.getSeller());
        }
        return erepo.save(e);
    }

    // Get all equipments
    public List<Equipment> getAllEquip() {
        return erepo.findAll();
    }

    // Find by id
    public Equipment findById(long id) {
        Optional<Equipment> opt = erepo.findById(id);
        return opt.orElse(null);
    }

    // Update equipment (preserve logic style)
    public Equipment updateEquipment(long id, Equipment updated) {
        Optional<Equipment> opt = erepo.findById(id);
        if (opt.isPresent()) {
            Equipment exist = opt.get();
            exist.setName(updated.getName());
            exist.setDescription(updated.getDescription());
            exist.setPrice(updated.getPrice());
            exist.setRating(updated.getRating());
            exist.setAvailability(updated.getAvailability());
            exist.setQuantityAvailable(updated.getQuantityAvailable());
            exist.setShippingPolicy(updated.getShippingPolicy());
            exist.setRefundPolicy(updated.getRefundPolicy());
            exist.setPrimaryImage(updated.getPrimaryImage());
            exist.setCategories(updated.getCategories());
            exist.setTags(updated.getTags());
            exist.setSecondaryImages(updated.getSecondaryImages());
            if (updated.getSeller() != null) {
                srepo.save(updated.getSeller());
                exist.setSeller(updated.getSeller());
            }
            return erepo.save(exist);
        }
        return null;
    }

    // Delete equipment
    public String deleteEquipment(long id) {
        if (erepo.existsById(id)) {
            erepo.deleteById(id);
            return "Equipment deleted successfully.";
        }
        return "Equipment not found.";
    }

    // add review
    public Equipment addReview(long id, Review re) {
        Optional<Equipment> e = erepo.findById(id);
        if (e.isPresent()) {
            Equipment found = e.get();
            found.getReviews().add(re);
            return erepo.save(found);
        }
        return null;
    }
}
