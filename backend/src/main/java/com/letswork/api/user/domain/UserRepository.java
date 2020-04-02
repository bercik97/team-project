package com.letswork.api.user.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<UserEntity, Long> {

    void save(UserEntity user);

    boolean existsByEmail(String email);

    void delete(UserEntity user);

    @Modifying
    @Query("UPDATE UserEntity user SET user.isEnabled = true WHERE user = ?1")
    void enableUserAccount(UserEntity user);
}
