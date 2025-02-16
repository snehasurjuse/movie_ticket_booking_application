package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.NotFoundException;
import com.MovieTicketBookingApplication.demo.exception.SeatAlreadyBookedException;
import com.MovieTicketBookingApplication.demo.model.Seat;
import com.MovieTicketBookingApplication.demo.model.Showtime;
import com.MovieTicketBookingApplication.demo.model.Theater;
import com.MovieTicketBookingApplication.demo.repository.SeatRepository;
import com.MovieTicketBookingApplication.demo.repository.ShowtimeRepository;
import com.MovieTicketBookingApplication.demo.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

//    public List<AvailableSeatDTO> getAvailableSeats(int showtimeId) {
//        Showtime showtime = showtimeRepository.findById(showtimeId)
//                .orElseThrow(() -> new NotFoundException("showtime not found"));
//
//        List<Seat> availableSeats = seatRepository.findByShowtimeAndIsBookedFalse(showtime);
//
//        // Map each seat to an AvailableSeatDTO
//        return availableSeats.stream().map(seat -> new AvailableSeatDTO(
//                showtime.getMovie().getTitle(),              // Movie title from Showtime
//                seat.getTheater().getLocation(),             // Theater location from Seat
//                showtime.getShowTime(),                      // Showtime date/time
//                showtime.getScreen().getScreenName()         // Screen name from Showtime
//        )).collect(Collectors.toList());
//    }

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

    public ShowLayoutDTO getSeatLayout(int showtimeId) {
        // 1. Fetch the showtime
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NotFoundException("Showtime not found"));

        // 2. Fetch all seats for this showtime
        List<Seat> seats = seatRepository.findByShowtimeId(showtime.getId());

        // 3. Build the top-level ShowLayoutDTO
        ShowLayoutDTO layoutDTO = new ShowLayoutDTO();
        layoutDTO.setMovieTitle(showtime.getMovie().getTitle());
        Theater theater = seats.get(0).getTheater();
        layoutDTO.setTheaterName(theater.getName());
        layoutDTO.setTheaterLocation(showtime.getScreen().getScreenName());
        // Or if you store location in Theater, do seat.getTheater().getLocation()

        // Format your date/time
        layoutDTO.setShowDate(formatShowDateTime(showtime.getShowTime()));

        // 4. Group seats by category
        Map<String, List<Seat>> seatsByCategory = seats.stream()
                .collect(Collectors.groupingBy(Seat::getCategory));

        // 5. Build seatCategories
        List<SeatCategoryDTO> categoryDTOs = new ArrayList<>();
        for (Map.Entry<String, List<Seat>> entry : seatsByCategory.entrySet()) {
            String category = entry.getKey();
            List<Seat> seatsInCategory = entry.getValue();

            // Assume all seats in the same category share the same price
            double price = seatsInCategory.isEmpty() ? 0 : seatsInCategory.get(0).getPrice();

            SeatCategoryDTO categoryDTO = new SeatCategoryDTO();
            categoryDTO.setCategoryName(category);
            categoryDTO.setPrice(price);

            // 6. Group seats by row label
            Map<String, List<Seat>> seatsByRow = seatsInCategory.stream()
                    .collect(Collectors.groupingBy(Seat::getRowLabel));

            List<SeatRowDTO> rowDTOs = new ArrayList<>();
            for (Map.Entry<String, List<Seat>> rowEntry : seatsByRow.entrySet()) {
                String rowLabel = rowEntry.getKey();
                List<Seat> rowSeats = rowEntry.getValue();

                // Sort seats in ascending order by seatNumber
                rowSeats.sort(Comparator.comparingInt(Seat::getSeatNumber));

                // Build SeatRowDTO
                SeatRowDTO rowDTO = new SeatRowDTO();
                rowDTO.setRowLabel(rowLabel);

                // Map each Seat -> SeatDTO
                List<SeatDTO> seatDTOs = rowSeats.stream().map(s -> {
                    SeatDTO seatDTO = new SeatDTO();
                    seatDTO.setSeatNumber(s.getSeatNumber());
                    seatDTO.setStatus(s.isBooked() ? "SOLD" : "AVAILABLE");
                    return seatDTO;
                }).collect(Collectors.toList());

                rowDTO.setSeats(seatDTOs);
                rowDTOs.add(rowDTO);
            }

            categoryDTO.setRows(rowDTOs);
            categoryDTOs.add(categoryDTO);
        }

        layoutDTO.setSeatCategories(categoryDTOs);
        return layoutDTO;
    }

    // Helper method to format LocalDateTime -> "Sunday, Feb 16, 2025, 02:45 PM"
    private String formatShowDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy, hh:mm a");
        return dateTime.format(formatter);
    }
}
