package com.jsp.theGreenCorner.Contorller;

import com.jsp.theGreenCorner.Entity.User;
import com.jsp.theGreenCorner.Services.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User endpoints:
 * GET    http://localhost:8080/user
 * POST   http://localhost:8080/user/signup
 * POST   http://localhost:8080/user/signin/{email}/{password}
 * GET    http://localhost:8080/user/{id}
 * PUT    http://localhost:8080/user/{id}
 * DELETE http://localhost:8080/user/{id}
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServices ser;

    public UserController(UserServices ser) {
        this.ser = ser;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(ser.getAllUser());
    }

    @PostMapping("/signup")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ser.singup(user));
    }

    @PostMapping("/signin/{email}/{password}")
    public ResponseEntity<User> signinUser(@PathVariable String email, @PathVariable String password) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ser.singin(email, password));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        User u = ser.findById(id);
        if (u == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(u);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User updated) {
        User u = ser.updateUser(id, updated);
        if (u == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(u);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok(ser.deleteUser(id));
    }
}
