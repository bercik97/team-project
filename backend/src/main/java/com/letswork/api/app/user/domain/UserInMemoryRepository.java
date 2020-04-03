package com.letswork.api.app.user.domain;

import lombok.AllArgsConstructor;

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
    public void delete(UserEntity user) {
        map.remove(user.getEmail());
    }

    @Override
    public void enableUserAccount(UserEntity user) {
        UserEntity userEntity = map.get(user.getEmail());
        userEntity.setEnabled(true);
    }
}
