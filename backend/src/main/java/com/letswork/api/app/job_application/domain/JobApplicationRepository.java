package com.letswork.api.app.job_application.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

interface JobApplicationRepository extends Repository<JobApplicationEntity, Long> {

    void save(JobApplicationEntity jobApplication);

    List<JobApplicationEntity> findAllByAdvertisementId(Long advertisementId);
}
