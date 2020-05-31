package com.letswork.api.app.job_application.domain;

import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto;
import com.letswork.api.app.job_application.domain.dto.JobApplicationDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class JobApplicationFacade {

    private final JobApplicationService service;

    public void add(CreateJobApplicationDto dto, String email) {
        service.add(dto, email);
    }

    public List<JobApplicationDto> findAllByAdvertisementId(Long advertisementId) {
        return service.findAllByAdvertisementId(advertisementId);
    }
}
