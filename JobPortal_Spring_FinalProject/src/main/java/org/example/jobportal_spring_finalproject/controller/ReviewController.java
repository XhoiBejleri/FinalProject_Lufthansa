package org.example.jobportal_spring_finalproject.controller;

import org.example.jobportal_spring_finalproject.model.dto.ReviewDTO;
import org.example.jobportal_spring_finalproject.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> getReviews(@RequestParam Long jobId,
                                                      @RequestParam(required = false) Integer rating,
                                                      Pageable pageable) {
        if (rating != null) {
            return ResponseEntity.ok(reviewService.getReviewsByJobAndRating(jobId, rating, pageable));
        } else {
            return ResponseEntity.ok(reviewService.getReviewsByJob(jobId, pageable));
        }
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@RequestParam Long jobId,
                                               @Valid @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.addReview(jobId, reviewDTO));
    }
}
