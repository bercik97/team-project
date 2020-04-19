package com.letswork.api.app.advertisement.domain.dto;

import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.category.domain.CategoryEntity;
import com.letswork.api.app.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String authorEmail;
    private String categoryName;

    public static AdvertisementDto convert(AdvertisementEntity advertisement) {
        UserEntity user = advertisement.getUser();
        CategoryEntity category = advertisement.getCategory();
        return AdvertisementDto.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .content(advertisement.getContent())
                .date(advertisement.getDate())
                .authorEmail(user.getEmail())
                .categoryName(category.getName())
                .build();
    }
}
