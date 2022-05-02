package com.example.api.model.services;

import com.example.api.model.entities.User;
import com.example.api.model.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepo;

    public List<User> listUsers() {
        return usersRepo.findAll();
    }

    public List<User> listUsersThroughAge(Integer age) {
        return usersRepo.findByAge(age);
    }

    public long countThroughAge(Integer age) {
        return usersRepo.countByAge(age);
    }

    public List<User> listThroughAgeLessThan(Integer age) {
        return usersRepo.findByAgeLessThan(age);
    }

    public User queryUser(String id) {
        return usersRepo.findById(id).orElse(null);
    }

    public User newUser(User it) {
        return usersRepo.save(it);
    }

    public User editUser(User us) {
        User aux = null;
        if (usersRepo.existsById(us.getIdUser())) aux = usersRepo.save(us);
        return aux;
    }

    public User eraseUser(String id) {
        User res = usersRepo.findById(id).orElse(null);
        if (res != null) usersRepo.deleteById(id);
        return res;
    }
}
