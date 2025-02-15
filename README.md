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

-   spring.datasource.url=jdbc:mysql://localhost:3306/movie_ticket_booking?serverTimezone=UTC
-   spring.datasource.username=root
-   spring.datasource.password=your_mysql_password
-   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
-   spring.jpa.hibernate.ddl-auto=update
-   spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

3. Build the project using Maven: mvn clean install

4. Run the application: mvn spring-boot:run

5. The application will be accessible at http://localhost:8080

#   Database Setup

  This project uses MySQL as the database. Follow these steps to set up the database:
  
  Install MySQL on your local machine.
  Create a new database named movie_ticket_booking.
  Update the database configuration in application.properties file.

### API Endpoints

1. User APIs
-    Register: POST /api/users/register
-    Login: POST /api/users/login

2. Movie APIs (Admin Only for modifications)
-    Add Movie: POST /api/movies/addMovie
-    Get Movies: GET /api/movies
-    Update Movie: PUT /api/movies/updateMovie/{id}
-    Delete Movie: DELETE /api/movies/deleteByTitle/{title}

3. Theater APIs (Admin Only for modifications)
-    Add Theater: POST /api/theaters/add
-    Get All Theaters: GET /api/theaters/getAll
-    Update Theater: PUT /api/theaters/update/{id}
-    Delete Theater: DELETE /api/theaters/delete/{id}

4. Showtime APIs (Admin Only for modifications)
-    Add Showtime: POST /api/showtimes
-    Get All Showtimes: GET /api/showtimes
-    Get Showtime by ID: GET /api/showtimes/{id}

5. Seat APIs
-    Get Available Seats: GET /seats/available/{showtimeId}
-    Book a Seat: POST /seats/book/{seatId}
-    Cancel Seat Booking: PUT /seats/cancel/{seatId}

6. Ticket APIs
-    Book Ticket: POST /tickets/createTicket
-    Get Ticket by ID: GET /tickets/getById/{id}

7. Order APIs
-    Create Order (with Ticket Booking): POST /orders/createOrderWithTicket
-    Get Order by ID: GET /orders/get/{id}
-    Cancel Order: DELETE /orders/cancel/{id}

8.  Payment APIs
-    Process Payment: POST /api/payments/process
-    Get Payment by ID: GET /api/payments/get/{id}
-    Update Payment: PUT /api/payments/update/{id}
-    Delete Payment: DELETE /api/payments/delete/{id}
-    Food APIs (Optional)
-    Add Food Item: POST /api/food/add
-    Get All Food Items: GET /api/food/getAll
-    Update Food Item: PUT /api/food/update/{id}
-    Delete Food Item: DELETE /api/food/delete/{id}

### Transaction Management
-   Critical operations, such as order creation and payment processing, are annotated with @Transactional to ensure that either all operations complete successfully or none do. This maintains data consistency in case of failures.

### Global Exception Handling
-   A global exception handler is implemented using @RestControllerAdvice to ensure that errors like UserNotFoundException, MovieNotFoundException, TheaterNotFoundException, OrderNotFoundException, and PaymentNotFoundException are handled in a consistent manner.

