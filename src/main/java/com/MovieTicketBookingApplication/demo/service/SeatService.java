package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.NotFoundException;
import com.MovieTicketBookingApplication.demo.exception.SeatAlreadyBookedException;
import com.MovieTicketBookingApplication.demo.model.Seat;
import com.MovieTicketBookingApplication.demo.model.Showtime;
import com.MovieTicketBookingApplication.demo.repository.SeatRepository;
import com.MovieTicketBookingApplication.demo.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Seat> getAvailableSeats(int showtimeId){
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NotFoundException("showtime not found"));

        return seatRepository.findByShowtimeAndIsBookedFalse(showtime);
    }

    public String bookSeat(int seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new NotFoundException("Seat not found"));

        if(seat.isBooked()) {
            throw new SeatAlreadyBookedException("seat is already booked");
        }
        seat.setBooked(true);
        seatRepository.save(seat);
        return "Seat Booked Successfully!";
    }

    public String cancelSeatBooking(int seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new NotFoundException("Seat not found"));

        if(!seat.isBooked()) {
            throw new IllegalStateException("Seat not booked");
        }

        seat.setBooked(false);
        seatRepository.save(seat);
        return "Seat booking canceled successfully!";
    }

    public void addSeat(Seat seat) {
        seatRepository.save(seat);
    }

}
