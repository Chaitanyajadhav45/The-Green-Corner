package com.jsp.theGreenCorner.Services;

import com.jsp.theGreenCorner.Entity.SellerAddress;
import com.jsp.theGreenCorner.Repository.SellerAddRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerAddServices {

    private SellerAddRepo repo;

    public SellerAddServices(SellerAddRepo repo) {
        this.repo = repo;
    }

    public SellerAddress saveSeller(SellerAddress s) {
        return repo.save(s);
    }

    public List<SellerAddress> getAll() {
        return repo.findAll();
    }

    public SellerAddress findById(long id) {
        Optional<SellerAddress> opt = repo.findById(id);
        return opt.orElse(null);
    }

    public SellerAddress update(long id, SellerAddress updated) {
        Optional<SellerAddress> opt = repo.findById(id);
        if (opt.isPresent()) {
            SellerAddress exist = opt.get();
            exist.setStreet(updated.getStreet());
            exist.setCity(updated.getCity());
            exist.setState(updated.getState());
            exist.setCountry(updated.getCountry());
            exist.setPincode(updated.getPincode());
            return repo.save(exist);
        }
        return null;
    }

    public String delete(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "SellerAddress deleted successfully.";
        }
        return "SellerAddress not found.";
    }
}
