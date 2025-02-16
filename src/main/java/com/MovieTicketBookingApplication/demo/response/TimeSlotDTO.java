package com.MovieTicketBookingApplication.demo.response;

import java.util.List;

public class TimeSlotDTO {

    private String showTime;     // e.g., "02:45 PM"
    private double price;        // e.g., 600
    private String category;     // e.g., "PLATINUM"
    private List<SeatRowDTO> seatRows;

    public TimeSlotDTO(String showTime, double price, String category, List<SeatRowDTO> seatRows) {
        this.showTime = showTime;
        this.price = price;
        this.category = category;
        this.seatRows = seatRows;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SeatRowDTO> getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(List<SeatRowDTO> seatRows) {
        this.seatRows = seatRows;
    }
}
