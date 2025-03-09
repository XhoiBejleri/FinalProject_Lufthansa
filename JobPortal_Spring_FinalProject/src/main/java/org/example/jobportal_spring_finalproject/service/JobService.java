package org.example.jobportal_spring_finalproject.service;

import org.example.jobportal_spring_finalproject.mapper.JobMapper;
import org.example.jobportal_spring_finalproject.model.dto.JobDTO;
import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.example.jobportal_spring_finalproject.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    public JobDTO postJob(JobDTO jobDTO, User employer) {
        Job job = jobMapper.toJob(jobDTO);
        job.setEmployer(employer);
        Job savedJob = jobRepository.save(job);
        return jobMapper.toJobDTO(savedJob);
    }

    public Page<JobDTO> getJobsByEmployer(User employer, Pageable pageable) {
        Page<Job> jobs = jobRepository.findByEmployer(employer, pageable);
        return jobs.map(jobMapper::toJobDTO);
    }

    public Page<JobDTO> searchJobs(String keyword, String location, Pageable pageable) {
        Page<Job> jobs = jobRepository.findByTitleContainingOrLocationContaining(keyword, location, pageable);
        return jobs.map(jobMapper::toJobDTO);
    }
}
