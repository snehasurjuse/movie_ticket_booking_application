package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.Ticket;
import com.MovieTicketBookingApplication.demo.response.TicketResponseDTO;
import com.MovieTicketBookingApplication.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Get all tickets
    @GetMapping("/getAllTickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // Get ticket by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable int id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    // Create a new ticket
    @PostMapping("/createTicket")
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody Ticket ticket) {
        TicketResponseDTO createdTicket = ticketService.createTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    // Delete a ticket
    @DeleteMapping("/deleteTicket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable int id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Ticket deleted successfully");
    }
}
