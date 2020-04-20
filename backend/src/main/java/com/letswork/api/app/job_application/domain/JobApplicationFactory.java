package com.letswork.api.app.job_application.domain;

import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto;

public class JobApplicationFactory {

    public JobApplicationEntity create(String email, CreateJobApplicationDto dto, AdvertisementEntity advertisement) {
        return JobApplicationEntity.builder()
                .email(email)
                .message(dto.getMessage())
                .advertisement(advertisement)
                .build();
    }
}
