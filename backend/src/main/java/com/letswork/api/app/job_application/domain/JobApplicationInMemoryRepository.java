package com.letswork.api.app.job_application.domain;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
class JobApplicationInMemoryRepository implements JobApplicationRepository {

    private ConcurrentHashMap<Long, JobApplicationEntity> map;

    private static Long idCounter = 0L;

    @Override
    public void save(JobApplicationEntity jobApplication) {
        map.put(++idCounter, jobApplication);
    }
}
