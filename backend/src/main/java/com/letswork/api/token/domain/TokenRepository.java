package com.letswork.api.token.domain;

import org.springframework.data.repository.Repository;

interface TokenRepository extends Repository<TokenEntity, Long> {

    void save(TokenEntity token);

    void delete(TokenEntity token);
}
