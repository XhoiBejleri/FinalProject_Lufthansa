package org.example.jobportal_spring_finalproject.controller;

import org.example.jobportal_spring_finalproject.model.dto.JobDTO;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public Page<JobDTO> getJobs(@RequestParam(required = false) String title,
                                @RequestParam(required = false) String location,
                                Pageable pageable) {
        return jobService.searchJobs(title != null ? title : "", location != null ? location : "", pageable);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    @PostMapping
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody JobDTO jobDTO) {
        User employer = new User();
        employer.setId(1L);
        return ResponseEntity.ok(jobService.postJob(jobDTO, employer));
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable Long jobId, @Valid @RequestBody JobDTO jobDTO) {
        return ResponseEntity.ok(jobService.updateJob(jobId, jobDTO));
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok("Job deleted successfully");
    }
}