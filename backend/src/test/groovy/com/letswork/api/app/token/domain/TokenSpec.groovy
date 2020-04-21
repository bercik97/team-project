package com.letswork.api.app.token.domain

import com.letswork.api.app.token.domain.exception.InvalidTokenException
import com.letswork.api.app.user.domain.UserEntity
import org.mockito.Mockito
import org.springframework.mail.javamail.JavaMailSender
import spock.lang.Shared
import spock.lang.Specification

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

class TokenSpec extends Specification {

    @Shared
    private TokenFacade tokenFacade

    @Shared
    private JavaMailSender mailSenderMock = Mockito.mock(JavaMailSender.class)

    @Shared
    private ConcurrentHashMap<String, TokenEntity> db

    def setupSpec() {
        db = new ConcurrentHashMap<>()
        tokenFacade = new TokenConfiguration().tokenFacade(db, mailSenderMock)
    }

    def cleanup() {
        db.clear()
    }

    def 'Should find token by confirmation token'() {
        given: 'token'
        createTokens(1, getCurrentTimeInSecondsAsString())
        String confirmationToken = db.values()
                .stream()
                .findFirst()
                .get()
                .confirmationToken

        when: 'we try to find token by confirmation token'
        TokenEntity foundToken = tokenFacade.findTokenByConfirmationToken(confirmationToken)

        then: 'found token is not null'
        foundToken != null
    }

    def 'Should do not find token by confirmation token and throw exception'() {
        given: 'non existing token'
        String nonExistingConfirmationToken = 'absolutely non existing token'

        when: 'we try to find non existing token'
        tokenFacade.findTokenByConfirmationToken(nonExistingConfirmationToken)

        then: 'exception is thrown'
        InvalidTokenException exception = thrown()
        exception.message == InvalidTokenException.CAUSE.CONFIRMATION_TOKEN_NOT_EXISTS.message
    }

    def 'Should clear all expired tokens'() {
        when: 'we create 5 expired tokens and 3 non expires tokens'
        Long seconds = 10000L
        createTokens(5, getCurrentTimeInSecondsAsString() + seconds)
        createTokens(3, getCurrentTimeInSecondsAsString())

        and: 'db has got all of these added tokens'
        db.size() == 8

        and: 'we clear all expired tokens'
        tokenFacade.cleanAllExpiredTokens()

        then: 'db should contain only 3 tokens which all of them are non expired'
        db.size() == 3
    }

    def 'Should delete token'() {
        given: 'token'
        createTokens(1, getCurrentTimeInSecondsAsString())

        when: 'db has this token'
        TokenEntity token = db.values()
                .stream()
                .findFirst()
                .get()
        token != null

        and: 'we delete token'
        tokenFacade.deleteToken(token)

        then: 'db is empty'
        db.size() == 0
    }

    private void createTokens(int amount, Long dateInSeconds) {
        String confirmationToken;
        UserEntity user = new UserEntity()
        TokenEntity token
        for (int i = 0; i < amount; i++) {
            confirmationToken = UUID.randomUUID().toString()
            token = new TokenEntity(confirmationToken, String.valueOf(dateInSeconds), user)
            token.id = i + 1
            db.put(confirmationToken, token)
        }
    }

    private static long getCurrentTimeInSecondsAsString() {
        return TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis()));
    }
}
