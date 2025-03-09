package org.example.jobportal_spring_finalproject.mapper;

import org.example.jobportal_spring_finalproject.model.dto.ApplicationDTO;
import org.example.jobportal_spring_finalproject.model.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(source = "job.id", target = "jobId")
    ApplicationDTO toApplicationDTO(Application application);

    @Mapping(source = "jobId", target = "job.id")
    Application toApplication(ApplicationDTO applicationDTO);
}
