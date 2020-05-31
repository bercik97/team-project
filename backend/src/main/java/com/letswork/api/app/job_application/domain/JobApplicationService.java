package com.letswork.api.app.job_application.domain;

import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.advertisement.domain.AdvertisementFacade;
import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto;
import com.letswork.api.app.job_application.domain.dto.JobApplicationDto;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class JobApplicationService {

    private final JobApplicationRepository repository;
    private final JobApplicationFactory factory;
    private final JobApplicationValidator validator;
    private final AdvertisementFacade advertisementFacade;

    public void add(CreateJobApplicationDto dto, String email) {
        validator.validate(dto.getMessage());
        AdvertisementEntity advertisement = advertisementFacade.findById(dto.getAdvertisementId());
        JobApplicationEntity jobApplicationEntity = factory.create(email, dto, advertisement);
        repository.save(jobApplicationEntity);
    }

    public List<JobApplicationDto> findAllByAdvertisementId(Long advertisementId) {
        return repository
                .findAllByAdvertisementId(advertisementId)
                .stream()
                .map(JobApplicationDto::convert)
                .collect(Collectors.toList());
    }
}
