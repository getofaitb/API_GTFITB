package com.example.api.controllers;

import com.example.api.model.entities.Movie;
import com.example.api.model.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControllerMovies {
    private final MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<?> queryMovies() {
        List<Movie> res = movieService.listMovies();
        if (res != null) return ResponseEntity.ok(res);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@RequestBody Movie m) {
        try {
            movieService.addMovie(m);
            return new ResponseEntity<>(m, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> queryOneMovie(@PathVariable long id) {
        Movie m = movieService.queryUsingId(id);
        if (m != null) {
            return ResponseEntity.ok(m);
        } else return ResponseEntity.notFound().build();
    }

    @PutMapping("/movies")
    public ResponseEntity<?> editMovie(@RequestBody Movie m) {
        Movie res = movieService.editMovie(m);
        if (res != null) return ResponseEntity.ok(res);
        else return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable long id) {
        Movie res = movieService.deleteMovie(id);
        if (res != null) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}
