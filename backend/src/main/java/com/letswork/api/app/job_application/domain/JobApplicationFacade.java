package com.letswork.api.app.job_application.domain;

import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JobApplicationFacade {

    private final JobApplicationService service;

    public void add(CreateJobApplicationDto dto, String email) {
        service.add(dto, email);
    }
}
