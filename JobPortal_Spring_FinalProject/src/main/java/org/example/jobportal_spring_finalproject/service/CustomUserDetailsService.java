package org.example.jobportal_spring_finalproject.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Map<String, UserDetails> USERS = new HashMap<>();

    static {
        USERS.put("admin", User.withUsername("admin")
                .password("$2a$10$.U/s3RbtuwZAA2k198Fu4eLmIeSqVrzmYIn7h99s8X0gsalDuZhyy")
                .roles("ADMIN")
                .build());
        USERS.put("employer", User.withUsername("employer")
                .password("$2a$10$VUBKUxfyOPRKdoIYqZXLae7LtS8VLZWI9hW7/z.uifRTQuQk2iOEu")
                .roles("EMPLOYER")
                .build());
        USERS.put("jobseeker", User.withUsername("jobseeker")
                .password("$2a$10$XpCJhP5J0FDxnlJEYyCIr.j8DbtLGP9PadyQj0XhULkJm6ucm2zBK")
                .roles("JOB_SEEKER")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!USERS.containsKey(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return USERS.get(username);
    }
}
