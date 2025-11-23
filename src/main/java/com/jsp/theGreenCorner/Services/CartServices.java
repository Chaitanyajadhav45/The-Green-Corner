package com.jsp.theGreenCorner.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.theGreenCorner.Entity.Cart;
import com.jsp.theGreenCorner.Entity.Equipment;
import com.jsp.theGreenCorner.Entity.Plant;
import com.jsp.theGreenCorner.Entity.User;
import com.jsp.theGreenCorner.Repository.CartRepo;
import com.jsp.theGreenCorner.Repository.EquipmentRepo;
import com.jsp.theGreenCorner.Repository.PlantRepository;
import com.jsp.theGreenCorner.Repository.UserRepo;

@Service
public class CartServices {

    @Autowired
    private CartRepo crepo;
    @Autowired
    private PlantRepository prepo;
    @Autowired
    private UserRepo urepo;
    @Autowired
    private EquipmentRepo erepo;

    public CartServices(CartRepo crepo, PlantRepository prepo) {
        super();
        this.crepo = crepo;
        this.prepo = prepo;
    }

    public Cart addToCart(long pId, long uId) {
        User u = urepo.findById(uId).get();
        Plant p = prepo.findById(pId).get();

        Cart c = u.getCart();
        c.getPlant().add(p);
        return crepo.save(c);
    }

    public Cart addToCartEquip(long eId, long uId) {
        User u = urepo.findById(uId).get();
        Equipment e = erepo.findById(eId).get();

        Cart c = u.getCart();
        c.getEquip().add(e);
        return crepo.save(c);
    }

    public String deleteCart(long cartId) {
        if (crepo.existsById(cartId)) {
            crepo.deleteById(cartId);
            return "Cart deleted successfully.";
        }
        return "Cart not found.";
    }
    public Cart getCartByUser(long userId) {
        User u = urepo.findById(userId).orElse(null);
        return (u != null) ? u.getCart() : null;
    }
    public Cart removePlantFromCart(long userId, long plantId) {
        User user = urepo.findById(userId).orElse(null);
        if (user == null) return null;

        Cart cart = user.getCart();
        cart.getPlant().removeIf(p -> p.getId() == plantId);

        return crepo.save(cart);
    }
    public Cart removeEquipFromCart(long userId, long equipId) {
        User user = urepo.findById(userId).orElse(null);
        if (user == null) return null;

        Cart cart = user.getCart();
        cart.getEquip().removeIf(e -> e.getId() == equipId);

        return crepo.save(cart);
    }

    public String clearCart(long userId) {
        User user = urepo.findById(userId).orElse(null);
        if (user == null) return "User not found";

        Cart cart = user.getCart();
        cart.getPlant().clear();
        cart.getEquip().clear();

        crepo.save(cart);

        return "Cart cleared";
    }



}
