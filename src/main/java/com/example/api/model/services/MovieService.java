package com.example.api.model.services;

import com.example.api.model.entities.Movie;
import com.example.api.model.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }

    public Movie queryUsingId(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie deleteMovie(Long id) {
        Movie res = movieRepository.findById(id).orElse(null);
        if (res != null) movieRepository.deleteById(id);
        return res;
    }

    public Movie addMovie(Movie m) {
        return movieRepository.save(m);
    }

    public Movie editMovie(Movie m) {
        Movie res = null;
        if (movieRepository.existsById(m.getId())) res = movieRepository.save(m);
        return res;
    }


}

