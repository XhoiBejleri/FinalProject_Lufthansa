package org.example.jobportal_spring_finalproject.mapper;

import org.example.jobportal_spring_finalproject.model.dto.JobDTO;
import org.example.jobportal_spring_finalproject.model.entity.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobDTO toJobDTO(Job job);
    Job toJob(JobDTO jobDTO);
}
