package com.letswork.api.app.advertisement.domain.dto;

import com.letswork.api.app.advertisement.domain.AdvertisementEntity;
import com.letswork.api.app.category.domain.CategoryEntity;
import com.letswork.api.app.job_application.domain.JobApplicationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnAdvertisementDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime date;
    private String categoryName;
    private List<JobApplicationEntity> jobApplications;

    public static OwnAdvertisementDto convert(AdvertisementEntity advertisement) {
        CategoryEntity category = advertisement.getCategory();
        return OwnAdvertisementDto.builder()
                .id(advertisement.getId())
                .title(advertisement.getTitle())
                .content(advertisement.getContent())
                .date(advertisement.getDate())
                .categoryName(category.getName())
                .jobApplications(advertisement.getJobApplications())
                .build();
    }
}
