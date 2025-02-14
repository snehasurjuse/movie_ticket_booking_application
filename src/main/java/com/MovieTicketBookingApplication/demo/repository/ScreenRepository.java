package com.MovieTicketBookingApplication.demo.repository;

import com.MovieTicketBookingApplication.demo.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {
}
