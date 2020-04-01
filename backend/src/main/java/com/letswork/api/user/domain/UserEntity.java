package com.letswork.api.user.domain;

import com.letswork.api.shared.BaseEntity;
import com.letswork.api.token.domain.TokenEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private TokenEntity token;
}
