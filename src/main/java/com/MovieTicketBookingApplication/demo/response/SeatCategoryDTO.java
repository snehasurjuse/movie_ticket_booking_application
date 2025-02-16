package com.MovieTicketBookingApplication.demo.response;

import java.util.List;

public class SeatCategoryDTO {

    private String categoryName;  // e.g., "PLATINUM"
    private double price;         // e.g., 600
    private List<SeatRowDTO> rows;

    public SeatCategoryDTO(String categoryName, double price, List<SeatRowDTO> rows) {
        this.categoryName = categoryName;
        this.price = price;
        this.rows = rows;
    }

    public SeatCategoryDTO() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<SeatRowDTO> getRows() {
        return rows;
    }

    public void setRows(List<SeatRowDTO> rows) {
        this.rows = rows;
    }
}
