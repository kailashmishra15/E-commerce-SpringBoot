package com.mp.auth.service;

import org.springframework.stereotype.Service;

import com.mp.auth.model.User;
import com.mp.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String email){

        return userRepository.findByEmail(email)
                .orElseThrow();

    }

    public User update(User user){

        User existing = userRepository.findByEmail(user.getEmail())
                .orElseThrow();

        existing.setFullName(user.getFullName());

        return userRepository.save(existing);

    }

}