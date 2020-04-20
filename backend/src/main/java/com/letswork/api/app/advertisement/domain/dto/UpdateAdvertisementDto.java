package com.letswork.api.app.advertisement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAdvertisementDto {

    private String newTitle;
    private String newContent;
}
