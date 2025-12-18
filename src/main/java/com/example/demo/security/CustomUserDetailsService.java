package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Normally, load from DB. For now, dummy user for testing.
        User user = new User(1L, email, "password", "USER");

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(() -> "ROLE_" + user.getRole())
        );
    }
}
