package com.learn.junit5test.controller;

import com.learn.junit5test.dto.UserDTO;
import com.learn.junit5test.service.UserService;
import jakarta.jws.soap.SOAPBinding;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private  UserService userService;

    @GetMapping("/users")
public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO),HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
