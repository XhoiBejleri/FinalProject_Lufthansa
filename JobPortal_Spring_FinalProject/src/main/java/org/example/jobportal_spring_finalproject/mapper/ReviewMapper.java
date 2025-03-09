package org.example.jobportal_spring_finalproject.mapper;

import org.example.jobportal_spring_finalproject.model.dto.ReviewDTO;
import org.example.jobportal_spring_finalproject.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "job.id", target = "jobId")
    @Mapping(source = "employer.id", target = "employerId")
    ReviewDTO toReviewDTO(Review review);

    @Mapping(source = "jobId", target = "job.id")
    @Mapping(source = "employerId", target = "employer.id")
    Review toReview(ReviewDTO reviewDTO);
}
