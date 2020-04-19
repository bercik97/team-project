package com.letswork.api.app.advertisement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letswork.api.app.shared.BaseEntity;
import com.letswork.api.app.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "advertisements")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementEntity extends BaseEntity {

    private String title;

    private String content;

    private LocalDateTime date;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REMOVE)
    private UserEntity user;
}
