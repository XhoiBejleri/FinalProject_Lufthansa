package org.example.jobportal_spring_finalproject.service;

import jakarta.transaction.Transactional;
import org.example.jobportal_spring_finalproject.mapper.ApplicationMapper;
import org.example.jobportal_spring_finalproject.model.dto.ApplicationDTO;
import org.example.jobportal_spring_finalproject.model.entity.Application;
import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.repository.ApplicationRepository;
import org.example.jobportal_spring_finalproject.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final ApplicationMapper applicationMapper;
    private final EmailService emailService;

    public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository,
                              ApplicationMapper applicationMapper, EmailService emailService) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.applicationMapper = applicationMapper;
        this.emailService = emailService;
    }

    @Transactional
    public ApplicationDTO applyForJob(Long jobId, ApplicationDTO applicationDTO) {
        try {
            Job job = jobRepository.findById(jobId)
                    .orElseThrow(() -> new RuntimeException("Job not found"));
            Application application = applicationMapper.toApplication(applicationDTO);
            application.setJob(job);

            User jobSeeker = new User();
            jobSeeker.setId(2L);
            application.setJobSeeker(jobSeeker);

            application.setAppliedAt(LocalDateTime.now());
            application.setStatus("Pending");
            Application savedApplication = applicationRepository.save(application);
            return applicationMapper.toApplicationDTO(savedApplication);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public ApplicationDTO updateApplicationStatus(Long applicationId, String status) {
        try {
            Application application = applicationRepository.findById(applicationId)
                    .orElseThrow(() -> new RuntimeException("Application not found"));
            String oldStatus = application.getStatus();
            application.setStatus(status);
            Application updatedApplication = applicationRepository.save(application);

            if ("Pending".equalsIgnoreCase(oldStatus) &&
                    ("Approved".equalsIgnoreCase(status) || "Denied".equalsIgnoreCase(status))) {
                String recipientEmail = "xbejleri@gmail.com";
                String subject = "Application Status Update";
                String body = String.format(
                        "Your application for the job with ID %d has been updated.\nPrevious Status: %s\nNew Status: %s\nThank you for applying.",
                        application.getJob().getId(),
                        oldStatus,
                        status
                );
                try {
                    emailService.sendSimpleMessage(recipientEmail, subject, body);
                } catch (Exception e) {
                }
            }
            return applicationMapper.toApplicationDTO(updatedApplication);
        } catch (Exception e) {
            throw e;
        }
    }

    public Page<ApplicationDTO> getApplicationsByJobSeekerId(Long jobSeekerId, Pageable pageable) {
        User jobSeeker = new User();
        jobSeeker.setId(jobSeekerId);
        Page<Application> applications = applicationRepository.findByJobSeeker(jobSeeker, pageable);
        return applications.map(applicationMapper::toApplicationDTO);
    }
}
