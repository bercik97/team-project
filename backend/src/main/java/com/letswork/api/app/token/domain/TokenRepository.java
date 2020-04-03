package com.letswork.api.app.token.domain;

import org.springframework.data.repository.Repository;

import java.util.Set;

interface TokenRepository extends Repository<TokenEntity, Long> {

    void save(TokenEntity token);

    void delete(TokenEntity token);

    Set<TokenEntity> findAll();

    TokenEntity findByConfirmationToken(String confirmationToken);
}
