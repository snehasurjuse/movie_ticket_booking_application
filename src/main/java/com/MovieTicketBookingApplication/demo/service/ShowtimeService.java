package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.NotFoundException;
import com.MovieTicketBookingApplication.demo.model.Showtime;
import com.MovieTicketBookingApplication.demo.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeService {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    public Showtime addShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public Showtime getShowtimeById(int id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Showtime not found with id: " + id));
    }

    public List<Showtime> getShowtimesByMovie(int movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    public void deleteShowtime(int id) {
        if (!showtimeRepository.existsById(id)) {
            throw new NotFoundException("Showtime not found with id: " + id);
        }
        showtimeRepository.deleteById(id);
    }
}
