package com.example.api.model.services;

import com.example.api.model.entities.User;
import com.example.api.model.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public User queryByUsername(String username) {
        return usersRepository.findByUsername(username).orElse(null);
    }

    public User createNewUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        usersRepository.save(newUser);
        return newUser;
    }

    public List<User> listUsers() {
        return usersRepository.findAll();
    }


}