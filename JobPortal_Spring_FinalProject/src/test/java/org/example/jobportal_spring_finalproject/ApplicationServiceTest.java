package org.example.jobportal_spring_finalproject;

import org.example.jobportal_spring_finalproject.model.dto.ApplicationDTO;
import org.example.jobportal_spring_finalproject.mapper.ApplicationMapper;
import org.example.jobportal_spring_finalproject.model.entity.Application;
import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.repository.ApplicationRepository;
import org.example.jobportal_spring_finalproject.repository.JobRepository;
import org.example.jobportal_spring_finalproject.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.eq;


class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplicationMapper applicationMapper;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void applyForJob_ShouldReturnApplicationDTO() {
        // Given
        Long jobId = 1L;
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setResume("http://example.com/resume.pdf");
        applicationDTO.setJobId(jobId);

        Job job = new Job();
        job.setId(jobId);

        Application application = new Application();
        application.setResume("http://example.com/resume.pdf");
        application.setJob(job);
        application.setAppliedAt(LocalDateTime.now());
        application.setStatus("Pending");

        Application savedApplication = new Application();
        savedApplication.setResume("http://example.com/resume.pdf");
        savedApplication.setJob(job);
        savedApplication.setAppliedAt(LocalDateTime.now());
        savedApplication.setStatus("Pending");

        ApplicationDTO expectedDTO = new ApplicationDTO();
        expectedDTO.setResume("http://example.com/resume.pdf");
        expectedDTO.setJobId(jobId);

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
        when(applicationMapper.toApplication(applicationDTO)).thenReturn(application);
        when(applicationRepository.save(any(Application.class))).thenReturn(savedApplication);
        when(applicationMapper.toApplicationDTO(savedApplication)).thenReturn(expectedDTO);

        // When
        ApplicationDTO result = applicationService.applyForJob(jobId, applicationDTO);

        // Then
        assertNotNull(result);
        assertEquals("http://example.com/resume.pdf", result.getResume());
        verify(jobRepository, times(1)).findById(jobId);
        verify(applicationRepository, times(1)).save(any(Application.class));
    }

    @Test
    void getApplicationsByJobSeeker_ShouldReturnEmptyPage() {
        User jobSeeker = new User();
        jobSeeker.setId(2L);
        when(applicationRepository.findByJobSeeker(eq(jobSeeker), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<ApplicationDTO> result = applicationService.getApplicationsByJobSeeker(jobSeeker, Pageable.unpaged());
        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
    }
}
