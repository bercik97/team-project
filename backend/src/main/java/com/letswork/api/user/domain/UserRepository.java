package com.letswork.api.user.domain;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<UserEntity, Long> {

    void save(UserEntity user);
}
