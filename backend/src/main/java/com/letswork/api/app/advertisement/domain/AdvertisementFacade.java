package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.dto.AdvertisementDto;
import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import com.letswork.api.app.advertisement.domain.dto.OwnAdvertisementDto;
import com.letswork.api.app.advertisement.domain.dto.UpdateAdvertisementDto;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
public class AdvertisementFacade {

    private final AdvertisementService service;

    public void add(CreateAdvertisementDto dto, String userEmail) {
        service.add(dto, userEmail);
    }

    public List<AdvertisementDto> findAll() {
        return service.findAll();
    }

    public List<AdvertisementDto> findAllByCategoryName(String categoryName) {
        return service.findAllByCategoryName(categoryName);
    }

    public List<OwnAdvertisementDto> findAllByUserId(Long userId) {
        return service.findAllByUserId(userId);
    }

    public AdvertisementEntity findById(Long advertisementId) {
        return service.findById(advertisementId);
    }

    @Transactional
    public void deleteAll(String userEmail) {
        service.deleteAll(userEmail);
    }

    @Transactional
    public void deleteById(Long id, String userEmail) {
        service.deleteById(id, userEmail);
    }

    @Transactional
    public void update(Long id, UpdateAdvertisementDto dto) {
        service.update(id, dto);
    }
}
