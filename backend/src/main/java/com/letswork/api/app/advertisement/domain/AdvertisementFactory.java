package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import com.letswork.api.app.category.domain.CategoryEntity;
import com.letswork.api.app.user.domain.UserEntity;

import java.time.LocalDateTime;

class AdvertisementFactory {

    public AdvertisementEntity create(CreateAdvertisementDto dto, UserEntity user, CategoryEntity category) {
        return AdvertisementEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .date(LocalDateTime.now())
                .user(user)
                .category(category)
                .build();
    }
}
