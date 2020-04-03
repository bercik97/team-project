package com.letswork.api.app.token.domain;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
class TokenInMemoryRepository implements TokenRepository {

    private ConcurrentHashMap<String, TokenEntity> map;

    @Override
    public void save(TokenEntity token) {
        map.put(token.getConfirmationToken(), token);
    }

    @Override
    public void delete(TokenEntity token) {
        map.remove(token.getConfirmationToken());
    }

    @Override
    public Set<TokenEntity> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public TokenEntity findByConfirmationToken(String confirmationToken) {
        return map.values()
                .stream()
                .filter(token -> token.getConfirmationToken().equals(confirmationToken))
                .findFirst()
                .orElse(null);
    }
}
