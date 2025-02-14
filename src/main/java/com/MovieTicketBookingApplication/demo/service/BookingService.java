package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.BookingNotFoundException;
import com.MovieTicketBookingApplication.demo.exception.TicketNotFoundException;
import com.MovieTicketBookingApplication.demo.exception.UserDoesNotExists;
import com.MovieTicketBookingApplication.demo.model.Booking;
import com.MovieTicketBookingApplication.demo.model.Ticket;
import com.MovieTicketBookingApplication.demo.model.User;
import com.MovieTicketBookingApplication.demo.repository.BookingRepository;
import com.MovieTicketBookingApplication.demo.repository.TicketRepository;
import com.MovieTicketBookingApplication.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get booking by ID
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + id + " not found"));
    }

    // Get bookings by user ID
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Create a new booking
    public Booking createBooking(int userId, int ticketId, String bookingDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExists("User with ID " + userId + " not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + ticketId + " not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setTicket(ticket);
        booking.setBookingDate(bookingDate);

        return bookingRepository.save(booking);
    }

    // Delete a booking
    public void deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking with ID " + id + " not found"));
        bookingRepository.delete(booking);
    }
}

