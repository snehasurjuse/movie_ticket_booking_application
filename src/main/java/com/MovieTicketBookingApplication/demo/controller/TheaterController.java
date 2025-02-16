package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.Theater;
import com.MovieTicketBookingApplication.demo.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    // Get all theaters
    @GetMapping("/getAllTheaters")
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

    // Get theater by ID
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable int id) {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    // Add a new theater
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addTheater")
    public ResponseEntity<Theater> addTheater(@RequestBody Theater theater) {
        return ResponseEntity.status(HttpStatus.CREATED).body(theaterService.addTheater(theater));
    }

    // Update a theater
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("updateById/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable int id, @RequestBody Theater theater) {
        return ResponseEntity.ok(theaterService.updateTheater(id, theater));
    }

    // Delete a theater
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable int id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.ok("Theater deleted successfully!");
    }
}
