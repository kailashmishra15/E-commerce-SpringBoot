package com.mp.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/welcome")
public class WelcomeController {

    @GetMapping
    public ResponseEntity<Map<String, String>> welcome() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the application!");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}