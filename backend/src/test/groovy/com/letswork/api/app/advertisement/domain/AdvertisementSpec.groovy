package com.letswork.api.app.advertisement.domain

import com.letswork.api.app.advertisement.domain.dto.AdvertisementDto
import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto
import com.letswork.api.app.advertisement.domain.dto.UpdateAdvertisementDto
import com.letswork.api.app.advertisement.domain.exception.InvalidAdvertisementException
import com.letswork.api.app.category.domain.CategoryEntity
import com.letswork.api.app.category.domain.CategoryFacade
import com.letswork.api.app.user.domain.UserEntity
import com.letswork.api.app.user.domain.UserFacade
import com.letswork.api.app.user.domain.UserService
import org.mockito.Mockito
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime
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

    def 'Should find advertisements'() {
        given: '3 advertisements'
        addAdvertisements(3, 'mail@com')

        expect:
        advertisementFacade.findAll() != null
    }

    def 'Should find advertisements by given category name'() {
        given: '3 advertisements'
        addAdvertisements(3, 'mail@com')
        int totalDbSize = db.size()

        when: 'we set category to #Marketing#'
        String categoryName = 'Marketing'
        AdvertisementEntity advertisement = db.values()
                .stream()
                .findAny()
                .get()
        advertisement.getCategory().name = categoryName

        and: 'we find advertisements by category name which we already set'
        List<AdvertisementDto> advertisements = advertisementFacade.findAllByCategoryName(categoryName)

        then: 'advertisements count are equal not equal to total db size'
        advertisements.size() != totalDbSize
    }

    def 'Should find advertisement by id'() {
        given: '1 advertisements'
        addAdvertisements(1, 'mail@com')

        when: 'we try to find advertisement by id'
        AdvertisementEntity advertisement = advertisementFacade.findById(1L)

        then: 'found advertisement is not null'
        advertisement != null
    }

    def 'Should delete all advertisements'() {
        given: '3 advertisements with email'
        String userEmail = 'mail@com'
        addAdvertisements(3, userEmail)

        when: 'we delete all advertisements of given email'
        advertisementFacade.deleteAll(userEmail)

        then: 'db is empty'
        db.isEmpty()
    }

    def 'Should delete advertisements by id'() {
        given: '1 advertisements with email'
        String userEmail = 'mail@com'
        addAdvertisements(1, userEmail)

        when: 'we delete advertisements by id of given email'
        advertisementFacade.deleteById(1L, userEmail)

        then: 'db is empty'
        db.isEmpty()
    }

    def 'Should update title and content'() {
        given: '1 advertisements with email'
        String userEmail = 'mail@com'
        addAdvertisements(1, userEmail)
        AdvertisementEntity oldAdvertisement = db.values()
                .stream()
                .findAny()
                .get()
        String oldTitle = oldAdvertisement.title
        String oldContent = oldAdvertisement.content

        when: 'we try to update title and content of given advertisement'
        advertisementFacade.update(1L, new UpdateAdvertisementDto('newTitle', 'newContent'))

        then: 'current title and content of advertisement is not equal to old title and content'
        AdvertisementEntity advertisement = advertisementFacade.findById(1L)
        advertisement.title != oldTitle
        advertisement.content != oldContent
    }

    private addAdvertisements(int quantity, String userEmail) {
        for (int i = 1; i < quantity + 1; i++) {
            String value = String.valueOf(i)
            UserEntity user = new UserEntity()
            user.email = userEmail
            AdvertisementEntity advertisement =
                    new AdvertisementEntity(value, value, LocalDateTime.now(), user, new CategoryEntity(), Collections.emptyList())
            advertisement.setId(i)
            db.put(value, advertisement)
        }
    }
}
