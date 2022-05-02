package com.example.api.model.repositories;

import com.example.api.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, String> {
    List<User> findByAge(Integer a);

    long countByAge(Integer a);

    List<User> findByAgeLessThan(Integer a);
}
