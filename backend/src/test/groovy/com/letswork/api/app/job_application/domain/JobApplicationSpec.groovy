package com.letswork.api.app.job_application.domain

import com.letswork.api.app.advertisement.domain.AdvertisementEntity
import com.letswork.api.app.advertisement.domain.AdvertisementFacade
import com.letswork.api.app.advertisement.domain.AdvertisementService
import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto
import org.mockito.Mockito
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

class JobApplicationSpec extends Specification {

    @Shared
    private JobApplicationFacade jobApplicationFacade

    @Shared
    private ConcurrentHashMap<Long, JobApplicationEntity> db

    @Shared
    private AdvertisementFacade advertisementFacadeMock = Mock()

    @Shared
    private AdvertisementService advertisementServiceMock = Mockito.mock(AdvertisementService.class)

    def setupSpec() {
        db = new ConcurrentHashMap<>()
        jobApplicationFacade = new JobApplicationConfiguration().jobApplicationFacade(db, advertisementFacadeMock)
    }

    def cleanup() {
        db.clear()
    }

    def 'Should add job application into advertisement'() {
        given:
        AdvertisementEntity advertisement = new AdvertisementEntity('title', 'content', LocalDateTime.now(), null, null, Collections.emptyList())
        advertisement.setId(1L)

        when: 'we apply job application'
        Mockito.when(advertisementServiceMock.findById(advertisement.getId())).thenReturn(advertisement)
        jobApplicationFacade.add(new CreateJobApplicationDto('I want this job!', advertisement.getId()), 'tom@mail.com')

        then: 'system has this job application'
        db.size() == 1
    }
}
