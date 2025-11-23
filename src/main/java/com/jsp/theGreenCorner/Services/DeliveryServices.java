package com.jsp.theGreenCorner.Services;

import com.jsp.theGreenCorner.Entity.Delivery;
import com.jsp.theGreenCorner.Entity.User;
import com.jsp.theGreenCorner.Repository.DeliveryRepo;
import com.jsp.theGreenCorner.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServices {

    private final DeliveryRepo drepo;
    private final UserRepo urepo;

    public DeliveryServices(DeliveryRepo drepo, UserRepo urepo) {
        this.drepo = drepo;
        this.urepo = urepo;
    }

    public Delivery saveDelivery(long userId, Delivery d) {

        User user = urepo.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        user.getDeliveries().add(d);  // add delivery in list
        urepo.save(user);             // join table gets updated

        return d;
    }

    public List<Delivery> getAllDeliveries() {
        return drepo.findAll();
    }

    public Delivery getDeliveryById(long id) {
        return drepo.findById(id).orElse(null);
    }

    public String deleteDelivery(long id) {
        if (drepo.existsById(id)) {
            drepo.deleteById(id);
            return "Delivery deleted successfully.";
        }
        return "Delivery not found.";
    }
}
