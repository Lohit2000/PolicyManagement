package com.example.policymanagement.controller;

import com.example.policymanagement.model.UserInfo;
import com.example.policymanagement.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserInfo> getAllUsers() {
        return userService.getAllUsers();
    }
}
