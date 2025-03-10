package org.example.jobportal_spring_finalproject.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptHashGenerator {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Admin hash: " + encoder.encode("admin1234"));
        System.out.println("Employer hash: " + encoder.encode("employer1234"));
        System.out.println("Jobseeker hash: " + encoder.encode("jobseeker1234"));
    }
}