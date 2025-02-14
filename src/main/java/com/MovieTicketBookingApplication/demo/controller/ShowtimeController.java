package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.Showtime;
import com.MovieTicketBookingApplication.demo.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping("/addShowtime")
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime) {
        Showtime savedShowtime = showtimeService.addShowtime(showtime);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShowtime);
    }

    @GetMapping("/getAllShowtime")
    public ResponseEntity<List<Showtime>> getAllShowtimes() {
        return ResponseEntity.ok(showtimeService.getAllShowtimes());
    }

    @GetMapping("/getShowtimeById/{id}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable int id) {
        return ResponseEntity.ok(showtimeService.getShowtimeById(id));
    }

    @GetMapping("/getShowByMovie/{movieId}")
    public ResponseEntity<List<Showtime>> getShowtimesByMovie(@PathVariable int movieId) {
        return ResponseEntity.ok(showtimeService.getShowtimesByMovie(movieId));
    }

    @DeleteMapping("deleteShowById/{id}")
    public ResponseEntity<String> deleteShowtime(@PathVariable int id) {
        showtimeService.deleteShowtime(id);
        return ResponseEntity.ok("Showtime deleted successfully");
    }
}
