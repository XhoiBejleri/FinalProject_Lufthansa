package org.example.jobportal_spring_finalproject.controller;

import jakarta.validation.Valid;
import org.example.jobportal_spring_finalproject.model.dto.ApplicationDTO;
import org.example.jobportal_spring_finalproject.model.dto.JobDTO;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.service.ApplicationService;
import org.example.jobportal_spring_finalproject.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {

    private final JobService jobService;
    private final ApplicationService applicationService;

    public JobSeekerController(JobService jobService, ApplicationService applicationService) {
        this.jobService = jobService;
        this.applicationService = applicationService;
    }

    @GetMapping("/jobs")
    public Page<JobDTO> viewAllJobs(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) String location,
                                    Pageable pageable) {
        return jobService.searchJobs(title != null ? title : "", location != null ? location : "", pageable);
    }

    @PostMapping("/jobs/{jobId}/apply")
    public ResponseEntity<ApplicationDTO> applyForJob(@PathVariable Long jobId,
                                                      @Valid @RequestBody ApplicationDTO applicationDTO) {
        ApplicationDTO savedApp = applicationService.applyForJob(jobId, applicationDTO);
        return ResponseEntity.ok(savedApp);
    }

    @PostMapping("/resume")
    public ResponseEntity<String> uploadResume(@RequestBody String resume) {
        return ResponseEntity.ok("Resume uploaded: " + resume);
    }

    @GetMapping("/applications")
    public Page<ApplicationDTO> getApplications(Pageable pageable) {
        User jobSeeker = new User();
        jobSeeker.setId(2L);
        return applicationService.getApplicationsByJobSeeker(jobSeeker, pageable);
    }
}
