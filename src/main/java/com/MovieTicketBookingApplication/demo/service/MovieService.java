package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.MovieAlreadyExistsException;
import com.MovieTicketBookingApplication.demo.exception.MovieNotFoundException;
import com.MovieTicketBookingApplication.demo.model.Movie;
import com.MovieTicketBookingApplication.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Movie addMovie(Movie movie) {
        Optional<Movie> existingMovie = movieRepository.findByTitle(movie.getTitle());
        if(existingMovie.isPresent()) {
            throw new MovieAlreadyExistsException("Movie with titile " + movie.getTitle() + " is already exists");
        }
        return movieRepository.save(movie);
    }

    public List<Movie> addMovies(List<Movie> moviesList) {
        List<Movie> savedList = new ArrayList<>();

        for(Movie movie : moviesList) {
            if(movieRepository.findByTitle(movie.getTitle()).isPresent()) {
                throw new MovieAlreadyExistsException("Movie with title " + movie.getTitle() + "is already exists");
            }
            savedList.add(movieRepository.save(movie));
        }
        return savedList;
    }

    public Movie updateMovieDetails(int id, Movie movieDetails) {
        Optional<Movie> movie =  movieRepository.findById(id);
        if(movie.isPresent()) {
            Movie existingMovie = movie.get();
            existingMovie.setTitle(movieDetails.getTitle());
            existingMovie.setDirector(movieDetails.getDirector());
            existingMovie.setGenre(movieDetails.getGenre());
            existingMovie.setDuration(movieDetails.getDuration());
            existingMovie.setLanguage(movieDetails.getLanguage());
            return movieRepository.save(existingMovie);
        }
        throw new MovieNotFoundException("Movie not found with Id: " + id);
    }

    public void deleteMovie(String title) {
        Optional<Movie> existing = movieRepository.findByTitle(title);
        if(existing.isPresent()) {
            movieRepository.delete(existing.get());
        } else {
            throw new MovieNotFoundException("Movie with the titile " + title + " not found.");
        }
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with title: " + title));
    }

}
