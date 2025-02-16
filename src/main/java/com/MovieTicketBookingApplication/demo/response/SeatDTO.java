package com.MovieTicketBookingApplication.demo.response;

public class SeatDTO {
    private int seatNumber;      // e.g., 1, 2, 3...
    private String status;

    public SeatDTO(int seatNumber, String status) {
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public SeatDTO() {
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
