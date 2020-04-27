package com.letswork.api.app.user.domain;

import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
class UserInMemoryRepository implements UserRepository {

    private ConcurrentHashMap<String, UserEntity> map;

    @Override
    public void save(UserEntity user) {
        map.put(user.getEmail(), user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return map.values()
                .stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return map.values()
                .stream().filter(e -> e.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void deleteByEmail(String email) {
        map.values().removeIf(u -> u.getEmail().equals(email));
    }
}
