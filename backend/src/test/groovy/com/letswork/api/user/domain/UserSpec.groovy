package com.letswork.api.user.domain

import com.letswork.api.user.domain.dto.CreateUserDto
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.ConcurrentHashMap

class UserSpec extends Specification {

    @Shared
    private UserFacade facade

    @Shared
    private ConcurrentHashMap<String, UserEntity> db

    def setupSpec() {
        db = new ConcurrentHashMap<>()
        facade = new UserConfiguration().facade(db)
    }

    def 'Should add user'() {
        when: 'we add user'
        facade.create(new CreateUserDto('john.doe@mail.com', 'pass', 'pass'))

        then: 'system has user'
        db.size() == 1
    }
}
