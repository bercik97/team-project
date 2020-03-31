package com.letswork.api.user.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

interface UserRepository extends Repository<UserEntity, Long> {

    void save(UserEntity user);

    List<UserEntity> findAll();
}
