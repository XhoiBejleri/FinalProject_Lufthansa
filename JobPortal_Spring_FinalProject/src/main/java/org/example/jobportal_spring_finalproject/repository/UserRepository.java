package org.example.jobportal_spring_finalproject.repository;

import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.model.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByRole(Role role, Pageable pageable);
}
