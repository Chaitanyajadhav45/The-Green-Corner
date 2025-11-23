package com.jsp.theGreenCorner.Services;

import com.jsp.theGreenCorner.Entity.Cart;
import com.jsp.theGreenCorner.Entity.User;
import com.jsp.theGreenCorner.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepo repo;

    public UserServices(UserRepo repo) {
        this.repo = repo;
    }

    public User singup(User user) {
        Cart cart = new Cart();
        user.setCart(cart);
        return repo.save(user);
    }

    public List<User> getAllUser() {
        return repo.findAll();
    }

    public User singin(String email, String password) {
        User find = repo.findEmailPass(email, password);
        return find;
    }

    public User findById(long id) {
        Optional<User> opt = repo.findById(id);
        return opt.orElse(null);
    }

    public User updateUser(long id, User updated) {
        Optional<User> opt = repo.findById(id);
        if (opt.isPresent()) {
            User exist = opt.get();
            exist.setUserName(updated.getUserName());
            exist.setPassword(updated.getPassword());
            exist.setEmail(updated.getEmail());
            exist.setContact(updated.getContact());
            return repo.save(exist);
        }
        return null;
    }

    public String deleteUser(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "User deleted successfully.";
        }
        return "User not found.";
    }
}
