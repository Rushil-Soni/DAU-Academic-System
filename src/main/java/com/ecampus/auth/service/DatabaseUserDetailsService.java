package com.ecampus.auth.service;

import com.ecampus.auth.user.AuthUserDetails;
import com.ecampus.auth.user.UserDetailsRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository repository;

    public DatabaseUserDetailsService(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>> LOGIN ATTEMPT, username = [" + username + "]");

        AuthUserDetails user = repository.findWithName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        System.out.println("Resolved user = " + user.getUsername());
        System.out.println("Resolved role = " + user.getrole());

        return User.builder()
            .username(user.getUsername())
            .password(user.getpassword())
            .authorities(user.getrole()) // Spring expects "ROLE_XXX" or custom string
            .build();
    }
}