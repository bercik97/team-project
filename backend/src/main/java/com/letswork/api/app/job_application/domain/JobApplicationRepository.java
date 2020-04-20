package com.letswork.api.app.job_application.domain;

import org.springframework.data.repository.Repository;

public interface JobApplicationRepository extends Repository<JobApplicationEntity, Long> {

    void save(JobApplicationEntity jobApplication);
}
