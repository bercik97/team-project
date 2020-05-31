package com.letswork.api.app.job_application.domain.dto;

import com.letswork.api.app.job_application.domain.JobApplicationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {

    private Long id;
    private String email;
    private String message;

    public static JobApplicationDto convert(JobApplicationEntity jobApplication) {
        return JobApplicationDto.builder()
                .id(jobApplication.getId())
                .email(jobApplication.getEmail())
                .message(jobApplication.getMessage())
                .build();
    }
}
