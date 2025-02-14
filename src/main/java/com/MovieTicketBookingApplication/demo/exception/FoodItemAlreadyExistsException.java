package com.MovieTicketBookingApplication.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FoodItemAlreadyExistsException extends RuntimeException {
    public FoodItemAlreadyExistsException(String message) {
        super(message);
    }
}
