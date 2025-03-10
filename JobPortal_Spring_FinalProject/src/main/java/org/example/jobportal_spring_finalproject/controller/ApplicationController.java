package org.example.jobportal_spring_finalproject.controller;

import org.example.jobportal_spring_finalproject.model.dto.ApplicationDTO;
import org.example.jobportal_spring_finalproject.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> applyForJob(@RequestParam Long jobId,
                                                      @Valid @RequestBody ApplicationDTO applicationDTO) {
        return ResponseEntity.ok(applicationService.applyForJob(jobId, applicationDTO));
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationDTO> updateApplicationStatus(@PathVariable Long applicationId,
                                                                  @RequestParam String status) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, status));
    }

    @GetMapping
    public ResponseEntity<Page<ApplicationDTO>> getApplicationsByJobSeeker(@RequestParam Long jobSeekerId,
                                                                           Pageable pageable) {
        return ResponseEntity.ok(applicationService.getApplicationsByJobSeekerId(jobSeekerId, pageable));
    }
}
