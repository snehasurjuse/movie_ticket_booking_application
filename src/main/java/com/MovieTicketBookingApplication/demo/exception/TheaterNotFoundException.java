package com.MovieTicketBookingApplication.demo.exception;

public class TheaterNotFoundException extends RuntimeException {
    public TheaterNotFoundException(String message) {
        super(message);
    }
}
