package com.azure.user.service;

import com.azure.user.entity.User;
import com.azure.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String create(User user){
        userRepository.save(user);
        return "User created successfully!";
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
