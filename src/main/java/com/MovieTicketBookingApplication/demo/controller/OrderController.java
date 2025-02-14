package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.response.OrderResponseDTO;
import com.MovieTicketBookingApplication.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestParam int userId,
                                                @RequestParam int movieId,
                                                @RequestParam int showtimeId,
                                                @RequestParam int numberOfSeats,
                                                @RequestParam double price) {
        OrderResponseDTO createdOrder = orderService.createOrder(userId, movieId, showtimeId, numberOfSeats, price);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable int id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok("Order cancelled successfully");
    }
}

