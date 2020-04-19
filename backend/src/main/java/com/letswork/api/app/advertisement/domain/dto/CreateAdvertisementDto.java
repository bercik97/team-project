package com.letswork.api.app.advertisement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAdvertisementDto {

    private String title;
    private String content;
    private String categoryName;
}
