package com.letswork.api.app.advertisement.domain

import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto
import com.letswork.api.app.advertisement.domain.exception.InvalidAdvertisementException
import com.letswork.api.app.category.domain.CategoryFacade
import com.letswork.api.app.user.domain.UserEntity
import com.letswork.api.app.user.domain.UserFacade
import com.letswork.api.app.user.domain.UserService
import org.mockito.Mockito
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.ConcurrentHashMap

class AdvertisementSpec extends Specification {

    @Shared
    private AdvertisementFacade advertisementFacade

    @Shared
    private UserFacade userFacadeMock = Mock()

    @Shared
    private CategoryFacade categoryFacadeMock = Mock()

    @Shared
    private UserService userServiceMock = Mockito.mock(UserService.class)

    @Shared
    private ConcurrentHashMap<String, AdvertisementEntity> db

    def setupSpec() {
        db = new ConcurrentHashMap<>()
        advertisementFacade = new AdvertisementConfiguration().advertisementFacade(db, userFacadeMock, categoryFacadeMock)
    }

    def cleanup() {
        db.clear()
    }

    def 'Should add advertisement'() {
        given:
        String email = 'john.doe@mail.com'
        UserEntity user = new UserEntity(email, '123456', true, null, Collections.emptyList())

        when: 'we add advertisement'
        Mockito.when(userServiceMock.findByEmail(email)).thenReturn(user)
        advertisementFacade.add(new CreateAdvertisementDto('title', 'some content', 'IT'), email)

        then: 'system has advertisement'
        db.size() == 1
    }

    @Unroll
    def 'Should throw an exception cause title is null, empty or blank = [#title]'(String title) {
        when: 'we try to create an advertisement'
        advertisementFacade.add(new CreateAdvertisementDto(title, 'some content', 'IT'), 'john.doe@mail.com')

        then: 'exception is thrown'
        InvalidAdvertisementException exception = thrown()
        exception.message == InvalidAdvertisementException.CAUSE.TITLE_EMPTY.message

        where:
        title | _
        null  | _
        ''    | _
        '  '  | _
    }

    def 'Should throw an exception cause title length equals more than permissible length'() {
        when: 'we try to create an advertisement'
        String tooLongTitle = 'thisTitleIsAbsolutelyTooLongForEveryAdvertisementYouWantToAdd'
        advertisementFacade.add(new CreateAdvertisementDto(tooLongTitle, 'some content', 'IT'), 'john.doe@mail.com')

        then: 'exception is thrown'
        InvalidAdvertisementException exception = thrown()
        exception.message == InvalidAdvertisementException.CAUSE.TITLE_WRONG_LENGTH.message
    }

    @Unroll
    def 'Should throw an exception cause content is null, empty or blank = [#content]'(String content) {
        when: 'we try to create an advertisement'
        advertisementFacade.add(new CreateAdvertisementDto('title', content, 'IT'), 'john.doe@mail.com')

        then: 'exception is thrown'
        InvalidAdvertisementException exception = thrown()
        exception.message == InvalidAdvertisementException.CAUSE.CONTENT_EMPTY.message

        where:
        content | _
        null    | _
        ''      | _
        '  '    | _
    }

    def 'Should throw an exception cause content length equals more than permissible length'() {
        when: 'we try to create an advertisement'
        String tooLongContent = prepareMoreThan1000CharactersContentLength()
        advertisementFacade.add(new CreateAdvertisementDto('title', tooLongContent, 'IT'), 'john.doe@mail.com')

        then: 'exception is thrown'
        InvalidAdvertisementException exception = thrown()
        exception.message == InvalidAdvertisementException.CAUSE.CONTENT_WRONG_LENGTH.message
    }

    private static String prepareMoreThan1000CharactersContentLength() {
        String tooLongContent = 'thisContentIsAbsolutelyTooLongForEveryAdvertisementYouWantToAdd'
        StringBuilder sb = new StringBuilder()
        for (int i = 0; i < 20; i++) {
            sb.append(tooLongContent)
        }
        return sb.toString()
    }
}
