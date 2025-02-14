package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.TicketNotFoundException;
import com.MovieTicketBookingApplication.demo.model.Movie;
import com.MovieTicketBookingApplication.demo.model.Theater;
import com.MovieTicketBookingApplication.demo.model.Ticket;
import com.MovieTicketBookingApplication.demo.model.User;
import com.MovieTicketBookingApplication.demo.repository.MovieRepository;
import com.MovieTicketBookingApplication.demo.repository.TheaterRepository;
import com.MovieTicketBookingApplication.demo.repository.TicketRepository;
import com.MovieTicketBookingApplication.demo.repository.UserRepository;
import com.MovieTicketBookingApplication.demo.response.TicketResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(int id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));
    }

    public TicketResponseDTO createTicket(Ticket ticket) {
        // Fetch the full User, Movie, and Theater objects from the database
        Ticket finalTicket = ticket;
        User user = userRepository.findById(ticket.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + finalTicket.getUser().getId()));

        Ticket finalTicket1 = ticket;
        Movie movie = movieRepository.findById(ticket.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + finalTicket1.getMovie().getId()));

        Ticket finalTicket2 = ticket;
        Theater theater = theaterRepository.findById(ticket.getTheater().getId())
                .orElseThrow(() -> new RuntimeException("Theater not found with ID: " + finalTicket2.getTheater().getId()));

        // Set the fetched objects
        ticket.setUser(user);
        ticket.setMovie(movie);
        ticket.setTheater(theater);
        ticket = ticketRepository.save(ticket);

        return new TicketResponseDTO(
                ticket.getId(),
                user.getName(),
                movie.getTitle(),
                theater.getName(),
                theater.getLocation(),
                ticket.getShowTime()
        );
    }

    public void deleteTicket(int id) {
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }
}

