package com.mp.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.mp.auth.model.Notification;
import com.mp.auth.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public Notification create(@RequestBody Notification n){
        return service.create(n);
    }

    @GetMapping
    public List<Notification> getAll(){
        return service.getAll();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){

    	service.delete(id);

    }
}