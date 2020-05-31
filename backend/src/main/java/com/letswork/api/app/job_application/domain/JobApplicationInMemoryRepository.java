package com.letswork.api.app.job_application.domain;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@AllArgsConstructor
class JobApplicationInMemoryRepository implements JobApplicationRepository {

    private final ConcurrentHashMap<Long, JobApplicationEntity> map;

    private static Long idCounter = 0L;

    @Override
    public void save(JobApplicationEntity jobApplication) {
        map.put(++idCounter, jobApplication);
    }

    @Override
    public List<JobApplicationEntity> findAllByAdvertisementId(Long advertisementId) {
        return map.values()
                .stream()
                .filter(ja -> advertisementId.equals(ja.getAdvertisement().getId()))
                .collect(Collectors.toList());
    }
}
