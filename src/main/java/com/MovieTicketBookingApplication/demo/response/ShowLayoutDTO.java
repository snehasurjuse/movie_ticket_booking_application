package com.MovieTicketBookingApplication.demo.response;

import java.util.List;

public class ShowLayoutDTO {
    private String movieTitle;
    private String theaterName;
    private String theaterLocation;
    private String showDate;  // e.g., "Sunday, Feb 16, 2025"
    private List<SeatCategoryDTO> seatCategories;

    public ShowLayoutDTO(String movieTitle, String theaterName, String theaterLocation, String showDate, List<TimeSlotDTO> timeSlots) {
        this.movieTitle = movieTitle;
        this.theaterName = theaterName;
        this.theaterLocation = theaterLocation;
        this.showDate = showDate;
        this.seatCategories = seatCategories;
    }

    public ShowLayoutDTO() {
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterLocation() {
        return theaterLocation;
    }

    public void setTheaterLocation(String theaterLocation) {
        this.theaterLocation = theaterLocation;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public List<SeatCategoryDTO> getSeatCategories() {
        return seatCategories;
    }

    public void setSeatCategories(List<SeatCategoryDTO> seatCategories) {
        this.seatCategories = seatCategories;
    }
}

