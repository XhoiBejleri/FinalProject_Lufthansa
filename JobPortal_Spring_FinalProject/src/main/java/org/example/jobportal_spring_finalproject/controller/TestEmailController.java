package org.example.jobportal_spring_finalproject.controller;

import org.example.jobportal_spring_finalproject.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEmailController {

    private final EmailService emailService;

    public TestEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test-email")
    public ResponseEntity<String> sendTestEmail() {
        try {
            emailService.sendSimpleMessage("xbejleri@gmail.com", "Test Email", "This is a test email.");
            return ResponseEntity.ok("Test email sent");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed: " + e.getMessage());
        }
    }
}
