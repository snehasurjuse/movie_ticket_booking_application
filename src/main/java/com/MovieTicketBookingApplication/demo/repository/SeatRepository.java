package com.MovieTicketBookingApplication.demo.repository;

import com.MovieTicketBookingApplication.demo.model.Seat;
import com.MovieTicketBookingApplication.demo.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findByShowtimeId(int showtimeId);

    // Get available seats for a specific showtime
    List<Seat> findByShowtimeAndIsBookedFalse(Showtime showtime);

    // Get all booked seats for a specific showtime
    List<Seat> findByShowtimeAndIsBookedTrue(Showtime showtime);
}
