package com.letswork.api.app.job_application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.shared.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationEntity extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(length = 500, nullable = false)
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private AdvertisementEntity advertisement;
}
