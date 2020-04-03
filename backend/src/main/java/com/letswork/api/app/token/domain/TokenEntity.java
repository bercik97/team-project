package com.letswork.api.app.token.domain;

import com.letswork.api.app.shared.BaseEntity;
import com.letswork.api.app.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tokens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity extends BaseEntity {

    @Column(name = "confirmation_token", unique = true, length = 36)
    private String confirmationToken;

    @Column(name = "date_in_seconds", length = 11)
    private String dateInSeconds;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
