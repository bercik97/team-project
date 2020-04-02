package com.letswork.api.token.domain;

import org.springframework.data.repository.Repository;

import java.util.Set;

interface TokenRepository extends Repository<TokenEntity, Long> {

    void save(TokenEntity token);

    void delete(TokenEntity token);

    Set<TokenEntity> findAll();

    TokenEntity findById(Long id);

    TokenEntity findByConfirmationToken(String token);
}
