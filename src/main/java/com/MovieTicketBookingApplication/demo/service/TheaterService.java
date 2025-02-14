package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.TheaterNotFoundException;
import com.MovieTicketBookingApplication.demo.model.Theater;
import com.MovieTicketBookingApplication.demo.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    // Get all theaters
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    // Get theater by ID
    public Theater getTheaterById(int id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new TheaterNotFoundException("Theater with ID " + id + " not found"));
    }

    // Add a new theater
    public Theater addTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    // Update a theater
    public Theater updateTheater(int id, Theater updatedTheater) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new TheaterNotFoundException("Theater with ID " + id + " not found"));

        theater.setName(updatedTheater.getName());
        theater.setLocation(updatedTheater.getLocation());
        theater.setCapacity(updatedTheater.getCapacity());

        return theaterRepository.save(theater);
    }

    // Delete a theater
    public void deleteTheater(int id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new TheaterNotFoundException("Theater with ID " + id + " not found"));
        theaterRepository.delete(theater);
    }
}

