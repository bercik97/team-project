package com.letswork.api.app.advertisement.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

interface AdvertisementRepository extends Repository<AdvertisementEntity, Long> {

    void save(AdvertisementEntity advertisement);

    List<AdvertisementEntity> findAll();

    List<AdvertisementEntity> findAllByCategoryName(String categoryName);

    AdvertisementEntity findById(Long advertisementId);
}
