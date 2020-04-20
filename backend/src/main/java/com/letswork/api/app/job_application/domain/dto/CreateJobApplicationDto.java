package com.letswork.api.app.job_application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateJobApplicationDto {

    private String message;
    private Long advertisementId;
}
