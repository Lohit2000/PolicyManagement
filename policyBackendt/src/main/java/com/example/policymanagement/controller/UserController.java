package com.example.policymanagement.controller;

import com.example.policymanagement.model.UserInfo;
import com.example.policymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserInfo> registerUser(@RequestBody UserInfo newUser) {
        if (newUser.getRole() == null || newUser.getRole().isEmpty()) {
            newUser.setRole("ROLE_USER");
        }
        UserInfo savedUser = userService.registerUser(newUser);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserInfo loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (userService.loginUser(email, password)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(
            @RequestHeader("email") String email,
            @RequestBody UserInfo updatedUser
    ) {
        Optional<UserInfo> userOptional = userService.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        UserInfo user = userOptional.get();

        UserInfo updated = userService.updateUser(updatedUser, user.getId());
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/deactivate")
    public ResponseEntity<?> deactivateUser(@RequestHeader("email") String email) {
        Optional<UserInfo> userOptional = userService.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        userService.deactivateUser(userOptional.get().getId());
        return ResponseEntity.ok("User deactivated successfully");
    }

//    @GetMapping("/details")
//    public ResponseEntity<?> getUserDetails(@RequestHeader("email") String email) {
//        Optional<User> userOptional = userService.getUserByEmail(email);
//        if (userOptional.isEmpty()) {
//            return ResponseEntity.status(404).body("User not found");
//        }
//
//        return ResponseEntity.ok(userOptional.get());
//    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("email") String email) {
        Optional<UserInfo> userOptional = userService.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        UserInfo user = userOptional.get();
        return ResponseEntity.ok(user);
    }
}
