package com.MovieTicketBookingApplication.demo.response;

public class TicketResponseDTO {
    private int ticketId;
    private String username;
    private String movieTitle;
    private String theaterName;
    private String showTime;
    private String theaterLocation;

    public TicketResponseDTO(int ticketId, String username, String movieTitle, String theaterName, String showTime, String time) {
        this.ticketId = ticketId;
        this.username = username;
        this.movieTitle = movieTitle;
        this.theaterName = theaterName;
        this.showTime = showTime;
        this.theaterLocation = theaterLocation;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getUsername() {
        return username;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public String getShowTime() {
        return showTime;
    }

    public String getTheaterLocation() {
        return theaterLocation;
    }
}
