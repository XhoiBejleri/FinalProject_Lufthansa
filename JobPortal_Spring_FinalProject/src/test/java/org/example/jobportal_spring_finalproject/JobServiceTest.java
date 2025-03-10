package org.example.jobportal_spring_finalproject;

import org.example.jobportal_spring_finalproject.model.dto.JobDTO;
import org.example.jobportal_spring_finalproject.mapper.JobMapper;
import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.repository.JobRepository;
import org.example.jobportal_spring_finalproject.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private JobMapper jobMapper;

    @InjectMocks
    private JobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void postJob_ShouldReturnJobDTO() {
        User employer = new User();
        employer.setId(1L);

        JobDTO jobDTO = new JobDTO();
        jobDTO.setTitle("Test Job");
        jobDTO.setDescription("Test Description");
        jobDTO.setLocation("Test Location");
        jobDTO.setSalary(new BigDecimal("50000"));

        Job job = new Job();
        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setLocation(jobDTO.getLocation());
        job.setSalary(jobDTO.getSalary());
        job.setEmployer(employer);

        when(jobMapper.toJob(jobDTO)).thenReturn(job);
        when(jobRepository.save(any(Job.class))).thenReturn(job);
        when(jobMapper.toJobDTO(job)).thenReturn(jobDTO);

        JobDTO result = jobService.postJob(jobDTO, employer);

        assertNotNull(result);
        assertEquals("Test Job", result.getTitle());
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    void getJobsByEmployer_ShouldReturnEmptyPageWhenNoJobs() {
        User employer = new User();
        employer.setId(1L);

        when(jobRepository.findByEmployer(eq(employer), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<JobDTO> result = jobService.getJobsByEmployer(employer, Pageable.unpaged());

        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
    }
}
