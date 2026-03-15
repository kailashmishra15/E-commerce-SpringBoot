package com.mp.auth.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp.auth.model.User;
import com.mp.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email){
        return userService.getUser(email);
    }

    @PutMapping("/update")
    public User update(@RequestBody User user){
        return userService.update(user);
    }

}
