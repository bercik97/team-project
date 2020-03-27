package com.letswork.api.user.domain;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
class UserInMemoryRepository implements UserRepository {

    private ConcurrentHashMap<String, UserEntity> map;

    @Override
    public void save(UserEntity user) {
        map.put(user.getEmail(), user);
    }
}
