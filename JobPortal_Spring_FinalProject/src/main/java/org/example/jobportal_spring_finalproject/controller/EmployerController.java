package org.example.jobportal_spring_finalproject.controller;

import jakarta.validation.Valid;
import org.example.jobportal_spring_finalproject.model.dto.ApplicationDTO;
import org.example.jobportal_spring_finalproject.model.dto.JobDTO;
import org.example.jobportal_spring_finalproject.model.dto.ReviewDTO;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.service.ApplicationService;
import org.example.jobportal_spring_finalproject.service.JobService;
import org.example.jobportal_spring_finalproject.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    private final JobService jobService;
    private final ApplicationService applicationService;
    private final ReviewService reviewService;

    public EmployerController(JobService jobService, ApplicationService applicationService, ReviewService reviewService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
        this.reviewService = reviewService;
    }

    @PostMapping("/jobs")
    public ResponseEntity<JobDTO> postJob(@Valid @RequestBody JobDTO jobDTO) {
        User employer = new User();
        employer.setId(1L);
        JobDTO savedJob = jobService.postJob(jobDTO, employer);
        return ResponseEntity.ok(savedJob);
    }

    @GetMapping("/jobs")
    public Page<JobDTO> getJobs(Pageable pageable) {
        User employer = new User();
        employer.setId(1L);
        return jobService.getJobsByEmployer(employer, pageable);
    }

    @GetMapping("/jobs/{jobId}/applications")
    public Page<ApplicationDTO> getApplications(@PathVariable Long jobId,
                                                @RequestParam(required = false) String status,
                                                Pageable pageable) {
        return applicationService.getApplicationsForJob(jobId, status, pageable);
    }

    @PutMapping("/applications/{applicationId}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(@PathVariable Long applicationId,
                                                                  @RequestParam String status) {
        ApplicationDTO updatedApp = applicationService.updateApplicationStatus(applicationId, status);
        return ResponseEntity.ok(updatedApp);
    }

    @PostMapping("/jobs/{jobId}/reviews")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable Long jobId,
                                               @Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO savedReview = reviewService.addReview(jobId, reviewDTO);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/jobs/{jobId}/reviews")
    public Page<ReviewDTO> getReviewsByJob(@PathVariable Long jobId,
                                           @RequestParam(required = false) Integer rating,
                                           Pageable pageable) {
        if (rating != null) {
            return reviewService.getReviewsByJobAndRating(jobId, rating, pageable);
        } else {
            return reviewService.getReviewsByJob(jobId, pageable);
        }
    }
}
