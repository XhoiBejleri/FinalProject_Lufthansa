package org.example.jobportal_spring_finalproject.repository;

import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByJob(Job job, Pageable pageable);

    Page<Review> findByJobAndRating(Job job, int rating, Pageable pageable);

}
