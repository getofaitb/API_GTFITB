package com.example.api.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private String idUser;
    private String password;
    private Integer age;
    private String favorite;
}
