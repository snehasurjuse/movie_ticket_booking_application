package com.MovieTicketBookingApplication.demo.repository;

import com.MovieTicketBookingApplication.demo.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
}
