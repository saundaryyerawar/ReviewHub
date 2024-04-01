package com.springproject.movies.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.movies.Model.Movie;
import com.springproject.movies.Repository.MovieRepository;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/allmovies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{imdbid}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable String imdbid){
        return new ResponseEntity<Optional<Movie>>(movieRepository.findByImdbId(imdbid),HttpStatus.OK);
    }
}
