package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.ScreenNotFoundException;
import com.MovieTicketBookingApplication.demo.model.Screen;
import com.MovieTicketBookingApplication.demo.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    // Retrieve all screens
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    // Retrieve a screen by its ID
    public Screen getScreenById(int id) {
        return screenRepository.findById(id)
                .orElseThrow(() -> new ScreenNotFoundException("Screen not found with ID: " + id));
    }

    // Add a new screen
    public Screen addScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    // Update an existing screen
    public Screen updateScreen(int id, Screen screenDetails) {
        Screen screen = getScreenById(id);
        screen.setScreenName(screenDetails.getScreenName());
        screen.setSeatCapacity(screenDetails.getSeatCapacity());
        // You may also update the list of showtimes if needed
        return screenRepository.save(screen);
    }

    // Delete a screen by its ID
    public void deleteScreen(int id) {
        Screen screen = getScreenById(id);
        screenRepository.delete(screen);
    }
}
