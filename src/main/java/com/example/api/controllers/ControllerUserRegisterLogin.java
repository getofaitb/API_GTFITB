package com.example.api.controllers;


import com.example.api.model.entities.User;
import com.example.api.model.entities.UserQueryDTO;
import com.example.api.model.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControllerUserRegisterLogin {
    private final UsersService usersService;


    @GetMapping("/me")
    public UserQueryDTO queryWhoAreYou(@AuthenticationPrincipal User usu) {
        return new UserQueryDTO(usu.getUsername(), usu.getAvatar(), usu.getRol());
    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
        try {
            User res = usersService.createNewUser(newUser);
            UserQueryDTO usu = new UserQueryDTO(res.getUsername(), res.getAvatar(), res.getRol());
            return new ResponseEntity<>(usu, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> listUsersDTO() {
        List<User> res = usersService.listUsers();
        List<UserQueryDTO> aux = new ArrayList();
        for (User usu : res) {
            aux.add(new UserQueryDTO(usu.getUsername(), usu.getAvatar(), usu.getRol()));
        }
        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(aux);
    }
}
