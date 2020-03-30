package com.letswork.api.user.domain;

import com.letswork.api.shared.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;
}
