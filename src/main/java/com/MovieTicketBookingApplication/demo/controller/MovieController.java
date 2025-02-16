package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.Movie;
import com.MovieTicketBookingApplication.demo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/batch")
    public ResponseEntity<?> addMovies(@RequestBody List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return ResponseEntity.badRequest().body("Movie list cannot be empty!");
        }

        List<Movie> savedMovies = movieService.addMovies(movies);
        return ResponseEntity.ok(savedMovies);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<Movie> updateMovieById(@PathVariable int id, @RequestBody Movie movieDetails) {
        return ResponseEntity.ok(movieService.updateMovieDetails(id, movieDetails));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteByTitle/{title}")
    public ResponseEntity<String> deleteMovieByTitle(@PathVariable String title) {
        movieService.deleteMovie(title);
        return ResponseEntity.ok("Movie with title " + title + " deleted successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        Movie movie = movieService.getMovieByTitle(title);
        return ResponseEntity.ok(movie);
    }

}
