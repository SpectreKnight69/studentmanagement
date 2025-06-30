package com.example.studentmanagement.controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminOnly() {
        return "Admin Dashboard - Authentication Working!";
    }
}