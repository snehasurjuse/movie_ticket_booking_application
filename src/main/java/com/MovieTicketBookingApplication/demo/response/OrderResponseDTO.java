package com.MovieTicketBookingApplication.demo.response;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class OrderResponseDTO {
    private int id;
    private String username;
    private String movieTitle;
    private String showTime;
    private int numberOfSeats;
    private double totalPrice;
    private String status;
    private LocalDateTime bookingTime;

    public OrderResponseDTO(int id, String username, String movieTitle, LocalDateTime showTime, int numberOfSeats, double totalPrice, String status, LocalDateTime bookingTime) {
        this.id = id;
        this.username = username;
        this.movieTitle = movieTitle;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.showTime = showTime.format(DateTimeFormatter.ofPattern(String.valueOf(formatter)));

        this.showTime = String.valueOf(showTime);
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
        this.status = status;
        this.bookingTime = bookingTime;
    }

    // Getters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getMovieTitle() { return movieTitle; }
    public String getShowTime() { return showTime; }
    public int getNumberOfSeats() { return numberOfSeats; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public LocalDateTime getBookingTime() { return bookingTime; }
}
