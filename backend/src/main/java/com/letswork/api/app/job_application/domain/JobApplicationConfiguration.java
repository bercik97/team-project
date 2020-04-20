package com.letswork.api.app.job_application.domain;

import com.letswork.api.app.advertisement.domain.AdvertisementFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class JobApplicationConfiguration {

    @Bean
    JobApplicationFacade jobApplicationFacade(JobApplicationRepository repository,
                                              AdvertisementFacade advertisementFacade) {
        JobApplicationFactory factory = new JobApplicationFactory();
        JobApplicationService service = new JobApplicationService(
                repository,
                factory,
                advertisementFacade
        );
        return new JobApplicationFacade(service);
    }

    JobApplicationFacade jobApplicationFacade(ConcurrentHashMap<Long, JobApplicationEntity> db,
                                              AdvertisementFacade advertisementFacade) {
        JobApplicationInMemoryRepository repository = new JobApplicationInMemoryRepository(db);
        JobApplicationFactory factory = new JobApplicationFactory();
        JobApplicationService service = new JobApplicationService(
                repository,
                factory,
                advertisementFacade
        );
        return new JobApplicationFacade(service);
    }
}
