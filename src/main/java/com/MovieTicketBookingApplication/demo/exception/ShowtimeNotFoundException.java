package com.MovieTicketBookingApplication.demo.exception;

public class ShowtimeNotFoundException extends RuntimeException{
    public ShowtimeNotFoundException(String message) {
        super(message);
    }
}
