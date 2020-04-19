package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AdvertisementFacade {

    private final AdvertisementService service;

    public void add(CreateAdvertisementDto dto, String userEmail) {
        service.add(dto, userEmail);
    }

    public List<AdvertisementEntity> findAllByUserEmail(String email) {
        return service.findAllByUserEmail(email);
    }
}
