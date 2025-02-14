package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.exception.MovieNotFoundException;
import com.MovieTicketBookingApplication.demo.exception.OrderNotFoundException;
import com.MovieTicketBookingApplication.demo.exception.ShowtimeNotFoundException;
import com.MovieTicketBookingApplication.demo.exception.UserNotFoundException;
import com.MovieTicketBookingApplication.demo.model.*;
import com.MovieTicketBookingApplication.demo.repository.MovieRepository;
import com.MovieTicketBookingApplication.demo.repository.OrderRepository;
import com.MovieTicketBookingApplication.demo.repository.ShowtimeRepository;
import com.MovieTicketBookingApplication.demo.repository.UserRepository;
import com.MovieTicketBookingApplication.demo.response.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public OrderResponseDTO createOrder(int userId, int movieId, int showtimeId, int numberOfSeats, double price) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        Showtime showtime = showtimeRepository.findById(showtimeId).orElseThrow(() -> new ShowtimeNotFoundException("Showtime not found"));

        Order order = new Order();
        order.setUser(user);
        order.setMovie(movie);
        order.setShowtime(showtime);
        order.setNumberOfSeats(numberOfSeats);
        order.setTotalPrice(price * numberOfSeats);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setBookingTime(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return new OrderResponseDTO(
                savedOrder.getId(),
                user.getName(),
                movie.getTitle(),
                showtime.getShowTime(),
                savedOrder.getNumberOfSeats(),
                savedOrder.getTotalPrice(),
                savedOrder.getStatus().name(),
                savedOrder.getBookingTime()
        );
    }

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> new OrderResponseDTO(
                order.getId(),
                order.getUser().getName(),
                order.getMovie().getTitle(),
                order.getShowtime().getShowTime(),
                order.getNumberOfSeats(),
                order.getTotalPrice(),
                order.getStatus().name(),
                order.getBookingTime()
        )).collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderById(int id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getName(),
                order.getMovie().getTitle(),
                order.getShowtime().getShowTime(),
                order.getNumberOfSeats(),
                order.getTotalPrice(),
                order.getStatus().name(),
                order.getBookingTime()
        );
    }

    public void cancelOrder(int id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}

