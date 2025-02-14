package com.MovieTicketBookingApplication.demo.exception;

public class UserDoesNotExists extends RuntimeException{

    public UserDoesNotExists(String message) {
        super(message);
    }
}
