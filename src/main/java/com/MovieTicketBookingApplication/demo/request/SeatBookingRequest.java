package com.MovieTicketBookingApplication.demo.request;

public class SeatBookingRequest {
    private int seatId;
    private int userId;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
