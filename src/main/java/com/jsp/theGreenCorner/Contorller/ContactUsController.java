package com.jsp.theGreenCorner.Contorller;

import com.jsp.theGreenCorner.Entity.ContactUs;
import com.jsp.theGreenCorner.Services.ContactUsServices;
import com.jsp.theGreenCorner.Services.EmailServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contact endpoints:
 * POST  http://localhost:8080/contact/save    -> save and send email to user's email
 * GET   http://localhost:8080/contact         -> get all messages
 * GET   http://localhost:8080/contact/{id}    -> get by id
 * PUT   http://localhost:8080/contact/{id}    -> update message
 * DELETE http://localhost:8080/contact/{id}  -> delete message
 */
@RestController
@RequestMapping("/contact")
public class ContactUsController {

    private final ContactUsServices ser;
    private final EmailServices emailServices;

    public ContactUsController(ContactUsServices ser, EmailServices emailServices) {
        this.ser = ser;
        this.emailServices = emailServices;
    }

    @PostMapping("/save")
    public ResponseEntity<ContactUs> saveMessage(@RequestBody ContactUs c) {
        ContactUs saved = ser.saveMessage(c);

        // send email to the email present in JSON
        emailServices.sendEmail(
                c.getEmail(),
                c.getSubject(),
                "Hello " + c.getName() + ",\n\n" + c.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<ContactUs>> getAllMessages() {
        return ResponseEntity.status(HttpStatus.FOUND).body(ser.getAllMessages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactUs> getById(@PathVariable long id) {
        ContactUs c = ser.findById(id);
        if (c == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactUs> update(@PathVariable long id, @RequestBody ContactUs updated) {
        ContactUs res = ser.updateMessage(id, updated);
        if (res == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(ser.deleteMessage(id));
    }
}
