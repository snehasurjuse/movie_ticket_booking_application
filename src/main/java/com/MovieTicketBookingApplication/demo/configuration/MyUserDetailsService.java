package com.MovieTicketBookingApplication.demo.configuration;

import com.MovieTicketBookingApplication.demo.model.User;
import com.MovieTicketBookingApplication.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userInfo = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this username: " + username));

        System.out.println("User found: " + userInfo.getEmail());
//        System.out.println("Stored password (hashed): " + userInfo.getPassword());

        return new UserDetailsInfo(userInfo);
    }
}
