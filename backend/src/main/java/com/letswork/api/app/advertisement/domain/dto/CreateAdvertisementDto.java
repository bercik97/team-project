package com.letswork.api.app.advertisement.domain.dto;

import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.category.domain.CategoryEntity;
import com.letswork.api.app.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateAdvertisementDto {

    private String title;
    private String content;
    private String categoryName;

    public static AdvertisementEntity create(CreateAdvertisementDto dto, UserEntity user, CategoryEntity category) {
        return AdvertisementEntity.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .date(LocalDateTime.now())
                .user(user)
                .category(category)
                .build();
    }
}
