package com.example.api.model.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String platform;
    private double duration;
    private int visualizations;
}
