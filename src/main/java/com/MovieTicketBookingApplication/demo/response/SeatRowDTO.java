package com.MovieTicketBookingApplication.demo.response;

import java.util.List;

public class SeatRowDTO {
    private String rowLabel;     // e.g., "A"
    private List<SeatDTO> seats;

    public SeatRowDTO(String rowLabel, List<SeatDTO> seats) {
        this.rowLabel = rowLabel;
        this.seats = seats;
    }

    public SeatRowDTO() {
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
