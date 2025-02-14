package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.Seat;
import com.MovieTicketBookingApplication.demo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    public SeatService seatService;

    @GetMapping("/available/{showtimeId}")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable int showtimeId) {
        List<Seat> availableSeats = seatService.getAvailableSeats(showtimeId);
        return ResponseEntity.ok(availableSeats);
    }

    // ✅ Book a seat
    @PostMapping("/book/{seatId}")
    public ResponseEntity<String> bookSeat(@PathVariable int seatId) {
        String response = seatService.bookSeat(seatId);
        return ResponseEntity.ok(response);
    }

    // ✅ Cancel a seat booking
    @PutMapping("/cancel/{seatId}")
    public ResponseEntity<String> cancelSeatBooking(@PathVariable int seatId) {
        String response = seatService.cancelSeatBooking(seatId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSeat(@RequestBody Seat seat) {
        seatService.addSeat(seat);
        return ResponseEntity.ok("Seat added successfully!");
    }

}
