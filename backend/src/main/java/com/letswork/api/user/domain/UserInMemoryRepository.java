package com.letswork.api.user.domain;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
class UserInMemoryRepository implements UserRepository {

    private ConcurrentHashMap<String, UserEntity> map;

    @Override
    public void save(UserEntity user) {
        map.put(user.getEmail(), user);
    }

    @Override
    public List<UserEntity> findAll() {
        return new ArrayList<>(map.values());
    }
}
