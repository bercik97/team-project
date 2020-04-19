package com.letswork.api.app.advertisement.domain

import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto
import com.letswork.api.app.user.domain.UserEntity
import com.letswork.api.app.user.domain.UserFacade
import com.letswork.api.app.user.domain.UserService
import org.mockito.Mockito
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.ConcurrentHashMap

class AdvertisementSpec extends Specification {

    @Shared
    private AdvertisementFacade advertisementFacade

    @Shared
    private UserFacade userFacadeMock = Mock()

    @Shared
    private UserService userServiceMock = Mockito.mock(UserService.class)

    @Shared
    private ConcurrentHashMap<String, AdvertisementEntity> db

    def setupSpec() {
        db = new ConcurrentHashMap<>()
        advertisementFacade = new AdvertisementConfiguration().advertisementFacade(db, userFacadeMock)
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
        advertisementFacade.add(new CreateAdvertisementDto('title', 'some content'), email)

        then: 'system has advertisement'
        db.size() == 1
    }
}
