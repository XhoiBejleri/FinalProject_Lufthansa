package org.example.jobportal_spring_finalproject.service;

import org.example.jobportal_spring_finalproject.mapper.ReviewMapper;
import org.example.jobportal_spring_finalproject.model.dto.ReviewDTO;
import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.Review;
import org.example.jobportal_spring_finalproject.repository.JobRepository;
import org.example.jobportal_spring_finalproject.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, JobRepository jobRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.jobRepository = jobRepository;
        this.reviewMapper = reviewMapper;
    }

    public ReviewDTO addReview(Long jobId, ReviewDTO reviewDTO) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        Review review = reviewMapper.toReview(reviewDTO);
        review.setJob(job);
        review.setCreatedAt(LocalDateTime.now());
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewDTO(savedReview);
    }

    public Page<ReviewDTO> getReviewsByJob(Long jobId, Pageable pageable) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        Page<Review> reviews = reviewRepository.findByJob(job, pageable);
        return reviews.map(reviewMapper::toReviewDTO);
    }

    public Page<ReviewDTO> getReviewsByJobAndRating(Long jobId, int rating, Pageable pageable) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        Page<Review> reviews = reviewRepository.findByJobAndRating(job, rating, pageable);
        return reviews.map(reviewMapper::toReviewDTO);
    }
}
