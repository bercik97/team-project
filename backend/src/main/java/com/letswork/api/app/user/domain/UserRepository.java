package com.letswork.api.app.user.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface UserRepository extends Repository<UserEntity, Long> {

    void save(UserEntity user);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
