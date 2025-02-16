package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.Screen;
import com.MovieTicketBookingApplication.demo.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    // Get all screens
    @GetMapping("/getAll")
    public ResponseEntity<List<Screen>> getAllScreens() {
        List<Screen> screens = screenService.getAllScreens();
        return ResponseEntity.ok(screens);
    }

    // Get a screen by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable int id) {
        Screen screen = screenService.getScreenById(id);
        return ResponseEntity.ok(screen);
    }

    // Add a new screen
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Screen> addScreen(@RequestBody Screen screen) {
        Screen savedScreen = screenService.addScreen(screen);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScreen);
    }

    // Update an existing screen
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Screen> updateScreen(@PathVariable int id, @RequestBody Screen screenDetails) {
        Screen updatedScreen = screenService.updateScreen(id, screenDetails);
        return ResponseEntity.ok(updatedScreen);
    }

    // Delete a screen
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteScreen(@PathVariable int id) {
        screenService.deleteScreen(id);
        return ResponseEntity.ok("Screen deleted successfully!");
    }
}

