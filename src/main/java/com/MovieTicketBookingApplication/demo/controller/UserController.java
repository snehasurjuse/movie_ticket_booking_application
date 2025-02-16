package com.MovieTicketBookingApplication.demo.controller;

import com.MovieTicketBookingApplication.demo.model.User;
import com.MovieTicketBookingApplication.demo.request.UserLoginRequest;
import com.MovieTicketBookingApplication.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("user/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try{
            userService.registerUser(user);
            return ResponseEntity.ok("User registered Successfully");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @PostMapping("/admin/register")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerAdmin(@RequestBody User user) {
        try {
            // The service method will check if an admin already exists.
            userService.registerAdmin(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest loginRequest) {
        String response = userService.loginUser(loginRequest);
        return ResponseEntity.ok(response);
    }

}
