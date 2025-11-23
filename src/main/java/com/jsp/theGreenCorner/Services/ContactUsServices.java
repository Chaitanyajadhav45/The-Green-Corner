package com.jsp.theGreenCorner.Services;

import com.jsp.theGreenCorner.Entity.ContactUs;
import com.jsp.theGreenCorner.Repository.ContactUsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactUsServices {

    @Autowired
    private final ContactUsRepo repo;

    public ContactUsServices(ContactUsRepo repo) {
        this.repo = repo;
    }

    public ContactUs saveMessage(ContactUs c) {
        return repo.save(c);
    }

    public List<ContactUs> getAllMessages() {
        return repo.findAll();
    }

    public ContactUs findById(long id) {
        Optional<ContactUs> opt = repo.findById(id);
        return opt.orElse(null);
    }

    public ContactUs updateMessage(long id, ContactUs updated) {
        Optional<ContactUs> opt = repo.findById(id);
        if (opt.isPresent()) {
            ContactUs exist = opt.get();
            exist.setName(updated.getName());
            exist.setEmail(updated.getEmail());
            exist.setSubject(updated.getSubject());
            exist.setMessage(updated.getMessage());
            return repo.save(exist);
        }
        return null;
    }

    public String deleteMessage(long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Contact message deleted successfully.";
        }
        return "Contact message not found.";
    }
}
