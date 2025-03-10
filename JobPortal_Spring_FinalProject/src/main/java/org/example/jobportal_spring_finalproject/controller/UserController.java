package org.example.jobportal_spring_finalproject.controller;

import org.example.jobportal_spring_finalproject.model.dto.UserDTO;
import org.example.jobportal_spring_finalproject.model.enums.Role;
import org.example.jobportal_spring_finalproject.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AdminService adminService;

    public UserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(@RequestParam(required = false) Role role, Pageable pageable) {
        return ResponseEntity.ok(adminService.getAllUsers(role, pageable));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
