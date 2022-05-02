package com.example.api.controllers;

import com.example.api.model.entities.User;
import com.example.api.model.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @CrossOrigin(origins = "http://localhost:9001")
    @GetMapping("/users")
    public List<User> listUsers(@RequestParam(value = "age", required = false) Integer age) {
        if (age == null) {
            return usersService.listUsers();
        } else return usersService.listThroughAgeLessThan(age);
    }

    @GetMapping("/users/{id}")
    public User queryUser(@PathVariable String id) {
        return usersService.queryUser(id);
    }

    @GetMapping("/users/age/{age}")
    public List<User> listUsersThroughAge(@PathVariable Integer age) {
        return usersService.listUsersThroughAge(age);
    }

    @GetMapping("/users/count/{age}")
    public long countThroughAge(@PathVariable Integer age) {
        return usersService.countThroughAge(age);
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User newUser) {
        return usersService.newUser(newUser);
    }

    @DeleteMapping("/users/{id}")
    public User eraseUser(@PathVariable String id) {
        return usersService.eraseUser(id);
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User editUser) {
        return usersService.editUser(editUser);
    }
}
