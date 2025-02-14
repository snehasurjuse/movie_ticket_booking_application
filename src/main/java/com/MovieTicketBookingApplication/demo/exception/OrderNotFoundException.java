package com.MovieTicketBookingApplication.demo.exception;

public class OrderNotFoundException  extends RuntimeException{

    public OrderNotFoundException(String message) {
        super(message);
    }
}
