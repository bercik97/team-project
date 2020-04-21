package com.letswork.api.app.job_application.domain

import com.letswork.api.app.advertisement.domain.AdvertisementEntity
import com.letswork.api.app.advertisement.domain.AdvertisementFacade
import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto
import com.letswork.api.app.job_application.domain.exceptions.InvalidJobApplicationException
import org.mockito.Mockito
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

class JobApplicationSpec extends Specification {

    @Shared
    private JobApplicationFacade jobApplicationFacade

    @Shared
    private ConcurrentHashMap<Long, JobApplicationEntity> db

    @Shared
    private AdvertisementFacade advertisementFacadeMock = Mockito.mock(AdvertisementFacade.class)

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
        Mockito.when(advertisementFacadeMock.findById(advertisement.getId())).thenReturn(advertisement)
        jobApplicationFacade.add(new CreateJobApplicationDto('I want this job!', advertisement.getId()), 'tom@mail.com')

        then: 'system has this job application'
        db.size() == 1
    }

    @Unroll
    def 'Should throw an exception cause message is null, empty or blank = [#message]'(String message) {
        when: 'we apply job application'
        jobApplicationFacade.add(new CreateJobApplicationDto(message, 1L), 'tom@mail.com')

        then: 'exception is thrown'
        InvalidJobApplicationException exception = thrown()
        exception.message == InvalidJobApplicationException.CAUSE.MESSAGE_EMPTY.message

        where:
        message | _
        null    | _
        ''      | _
        '  '    | _
    }

    def 'Should throw an exception cause message length equals more than permissible length'() {
        when: 'we apply job application'
        String tooLongMessage = prepareMoreThan1000CharactersMessageLength()
        jobApplicationFacade.add(new CreateJobApplicationDto(tooLongMessage, 1L), 'tom@mail.com')

        then: 'exception is thrown'
        InvalidJobApplicationException exception = thrown()
        exception.message == InvalidJobApplicationException.CAUSE.MESSAGE_WRONG_LENGTH.message
    }

    private static String prepareMoreThan1000CharactersMessageLength() {
        String tooLongMessage = 'thisMessageIsAbsolutelyTooLongForEveryAdvertisementYouWantToAdd'
        StringBuilder sb = new StringBuilder()
        for (int i = 0; i < 10; i++) {
            sb.append(tooLongMessage)
        }
        return sb.toString()
    }
}
