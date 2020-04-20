package com.letswork.api.app.job_application.domain;

import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.advertisement.domain.AdvertisementFacade;
import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class JobApplicationService {

    private final JobApplicationRepository repository;
    private final JobApplicationFactory factory;
    private final AdvertisementFacade advertisementFacade;

    public void add(CreateJobApplicationDto dto, String email) {
        AdvertisementEntity advertisement = advertisementFacade.findById(dto.getAdvertisementId());
        repository.save(factory.create(email, dto, advertisement));
    }
}
