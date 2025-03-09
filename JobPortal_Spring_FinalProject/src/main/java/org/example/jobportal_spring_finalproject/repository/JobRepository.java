package org.example.jobportal_spring_finalproject.repository;

import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByEmployer(User user, Pageable pageable);

    Page<Job> findByTitleContainingOrLocationContaining(String title, String location, Pageable pageable);
}
