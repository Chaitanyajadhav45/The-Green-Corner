package com.jsp.theGreenCorner.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jsp.theGreenCorner.Entity.Seller;
import com.jsp.theGreenCorner.Repository.SellerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class SellerServices {

    @Autowired
    private SellerRepo repo;

    public Seller saveSeller(Seller s) {
        return repo.save(s);
    }

    public List<Seller> getAll() {
        return repo.findAll();
    }

    public Seller findById(long id) {
        Optional<Seller> opt = repo.findById(id);
        return opt.orElse(null);
    }

    public Seller updateSeller(long id, Seller updated) {
        Optional<Seller> opt = repo.findById(id);
        if (opt.isPresent()) {
            Seller exist = opt.get();
            exist.setName(updated.getName());
            exist.setAddress(updated.getAddress());
            return repo.save(exist);
        }
        return null;
    }

    public String deleteSeller(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Seller deleted successfully.";
        }
        return "Seller not found.";
    }
}
