package org.example.jobportal_spring_finalproject.model.dto;

public class ApplicationDTO {

    private String resume;
    private Long jobId;



    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
