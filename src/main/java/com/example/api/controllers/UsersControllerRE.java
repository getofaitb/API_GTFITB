package com.example.api.controllers;

import com.example.api.model.entities.User;
import com.example.api.model.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UsersControllerRE {
    private final UsersService usersService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> queryUser(@PathVariable String id) {
        User res = usersService.queryUser(id);
        if (res == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(res);
    }

    @PostMapping("/user")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        User res = usersService.newUser(newUser);
        return new ResponseEntity<User>(res, HttpStatus.CREATED);
    }
}
