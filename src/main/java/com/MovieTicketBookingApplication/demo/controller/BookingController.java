package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.Booking;
import com.MovieTicketBookingApplication.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Get all bookings
    @GetMapping("/getAllBookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // Get booking by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    // Get bookings by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
    }

    // Create a new booking
    @PostMapping("/bookTicket")
    public ResponseEntity<Booking> createBooking(@RequestParam int userId,
                                                 @RequestParam int ticketId,
                                                 @RequestParam String bookingDate) {
        Booking booking = bookingService.createBooking(userId, ticketId, bookingDate);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    // Delete a booking
    @DeleteMapping("/cancleBooking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }
}

