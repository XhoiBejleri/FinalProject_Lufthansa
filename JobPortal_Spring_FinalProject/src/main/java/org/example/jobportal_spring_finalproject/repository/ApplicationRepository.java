package org.example.jobportal_spring_finalproject.repository;

import org.example.jobportal_spring_finalproject.model.entity.Application;
import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByJob(Job job, Pageable pageable);

    Page<Application> findByJobAndStatus(Job job, String status, Pageable pageable);

    Page<Application> findByJobSeeker(User jobSeeker, Pageable pageable);
}
