package com.MovieTicketBookingApplication.demo.repository;

import com.MovieTicketBookingApplication.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByUserId(int userId);
    List<Ticket> findByMovieId(int movieId);
}
