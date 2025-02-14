package com.MovieTicketBookingApplication.demo.service;

import com.MovieTicketBookingApplication.demo.configuration.JWTService;
import com.MovieTicketBookingApplication.demo.model.Role;
import com.MovieTicketBookingApplication.demo.model.User;
import com.MovieTicketBookingApplication.demo.repository.UserRepository;
import com.MovieTicketBookingApplication.demo.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }

    public User registerUser(User user) {
        try {
            if(userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email is already in use");
            }

            user.setPassword(encoder.encode(user.getPassword()));
//            user.setPassword(user.getPassword());
            user.setRole(Role.USER);
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Email is already registerd");
        }catch (Exception e) {
            throw new RuntimeException("An error occurred while registering the user.");
        }
    }

    public String loginUser(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid User email"));

        // Verify password manually before authentication
        if (!encoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(), userLoginRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userLoginRequest.getEmail());
        }

        throw new UsernameNotFoundException("Invalid User email");
    }


}
