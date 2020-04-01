package com.letswork.api.user.domain

import com.letswork.api.token.domain.TokenFacade
import com.letswork.api.user.domain.dto.CreateUserDto
import com.letswork.api.user.domain.exception.InvalidUserException
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.ConcurrentHashMap

class UserSpec extends Specification {

    @Shared
    private UserFacade userFacade

    @Shared
    private TokenFacade tokenFacade = Mock()

    @Shared
    private ConcurrentHashMap<String, UserEntity> db

    def setupSpec() {
        db = new ConcurrentHashMap<>()
        userFacade = new UserConfiguration().userFacade(db, tokenFacade)
    }

    def cleanup() {
        db.clear()
    }

    def 'Should add user'() {
        when: 'we add user'
        userFacade.create(new CreateUserDto('john.doe@mail.com', '123456', '123456'))

        then: 'system has user'
        db.size() == 1
    }

    @Unroll
    def 'Should throw an exception cause email is null, empty or blank = [#email]'(String email) {
        when: 'we try to create an user'
        userFacade.create(new CreateUserDto(email, '123456', '123456'))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.EMAIL_EMPTY.message

        where:
        email | _
        null  | _
        ''    | _
        '  '  | _
    }

    @Unroll
    def 'Should throw an exception cause email format = [#email]'(String email) {
        when: 'we try to create an user'
        userFacade.create(new CreateUserDto(email, '123456', '123456'))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.EMAIL_WRONG_FORMAT.message

        where:
        email                          | _
        'plainaddress'                 | _
        '#@%^%#$@#$@#.com'             | _
        '@domain.com'                  | _
        'Joe Smith <email@domain.com>' | _
        'email.domain.com'             | _
        'email@domain@domain.com'      | _
        '.email@domain.com'            | _
        'email.@domain.com'            | _
        'email..email@domain.com'      | _
        'email@domain.com (Joe Smith)' | _
        'email@domain'                 | _
    }

    def 'Should throw an exception cause email already exists in system'() {
        given:
        String email = 'a@a.com'

        when: 'we try to create an user with given email'
        userFacade.create(new CreateUserDto(email, '123456', '123456'))

        and: 'user is created'
        db.get(email) != null

        and: 'we try to create user with the same given email as before'
        userFacade.create(new CreateUserDto(email, '654321', '654321'))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.EMAIL_NOT_UNIQUE.message
    }

    @Unroll
    def 'Should throw an exception cause password is null, empty or blank = [#password]'(String password) {
        when: 'we try to create an user'
        userFacade.create(new CreateUserDto('a@a.com', password, password))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.PASSWORD_EMPTY.message

        where:
        password | _
        null     | _
        ''       | _
        '  '     | _
    }

    def 'Should throw an exception cause password length is less than 6'() {
        when: 'we try to create an user'
        userFacade.create(new CreateUserDto('a@a.com', '12345', '12345'))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.PASSWORD_WRONG_LENGTH.message
    }

    @Unroll
    def 'Should throw an exception cause password do not match confirmed password = [#password, #confirmedPassword]'(String password, String confirmedPassword) {
        when: 'we try to create an user'
        userFacade.create(new CreateUserDto('a@a.com', password, confirmedPassword))

        then: 'exception is thrown'
        InvalidUserException exception = thrown()
        exception.message == InvalidUserException.CAUSE.CONFIRMED_PASSWORD_DO_NOT_MATCH_PASSWORD.message

        where:
        password | confirmedPassword
        '123456' | '654321'
        '654321' | '123456'
    }
}
