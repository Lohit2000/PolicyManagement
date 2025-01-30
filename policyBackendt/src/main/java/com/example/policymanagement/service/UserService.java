package com.example.policymanagement.service;

import com.example.policymanagement.model.UserInfo;
import com.example.policymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserInfo registerUser(UserInfo user) {
        user.setDateOfRegistration(LocalDate.now());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }

        return userRepository.save(user);
    }

    public Optional<UserInfo> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean loginUser(String email, String password) {
        Optional<UserInfo> user = userRepository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    public boolean isAdmin(String email) {
        Optional<UserInfo> user = userRepository.findByEmail(email);
        return user.isPresent() && "ROLE_ADMIN".equals(user.get().getRole());
    }

    public UserInfo updateUser(UserInfo updatedUser, Long userId) {
        UserInfo existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(updatedUser.getName());
        existingUser.setContactNumber(updatedUser.getContactNumber());
        existingUser.setAddress(updatedUser.getAddress());

        return userRepository.save(existingUser);
    }

    public void deactivateUser(Long userId) {
        UserInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }

    public List<UserInfo> getAllUsers() {
        return userRepository.findAll();
    }
}
