# Movie Ticket Booking API - Spring Boot Project

This project is a Spring Boot implementation of the backend APIs for a movie ticket booking system—similar in spirit to platforms like BookMyShow. It provides a set of RESTful APIs that allow client applications (such as web or mobile apps) to interact with the system to perform various operations related to movie ticket booking.

## Features

- **User Management**
  - **User Registration & Login:** Users can create an account, log in, and manage their profile information.
  - **Role-based Access:** Differentiates between normal users (customers) and admin users. Admins have additional privileges like managing movies, theaters, and showtimes.

- **Movie Management**
  - **Admin Operations:** Admins can add, update, and delete movies.
  - **User Browsing:** Users can view the list of available movies.

- **Theater Management**
  - **Admin Operations:** Admins can add, update, and delete theaters.
  - **Theater Details:** Each theater includes its name, location, and seat capacity.

- **Showtime & Seat Management**
  - **Showtime Creation:** Admins can schedule showtimes for movies in specific theaters and screens.
  - **Seat Selection:** Users can view available seats for a given showtime and select their preferred seats.
  - **Ticket Booking:** Users can book tickets for a selected showtime, and the system ensures that booked seats are reserved.

- **Order & Payment Processing**
  - **Order Creation:** Users can create orders when booking tickets.
  - **Payment Processing:** Integrated payment APIs ensure that payments for ticket bookings (or food orders) are processed reliably. The operations are transactional so that, if any step fails, all changes are rolled back.

- **Food Ordering (Optional)**
  - **Food Menu Management:** Admins can manage food items available in theaters.
  - **User Orders:** Users can view the food menu and place orders along with their ticket bookings.

- **Booking History**
  - **Order Details:** Users can view their past bookings and payment details.

## Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL**
- **Maven**

## Getting Started

### Prerequisites

- Java 17 or later
- MySQL installed and running
- Maven installed

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/snehasurjuse/movie_ticket_booking_application.git
   cd movie_ticket_booking_application
2. Update the application.properties file with your database credentials
spring.datasource.url=jdbc:mysql://localhost:3306/movie_ticket_booking?serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
