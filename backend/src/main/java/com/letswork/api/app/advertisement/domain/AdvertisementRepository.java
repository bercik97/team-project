package com.letswork.api.app.advertisement.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

interface AdvertisementRepository extends Repository<AdvertisementEntity, Long> {

    void save(AdvertisementEntity advertisement);

    List<AdvertisementEntity> findAll();

    List<AdvertisementEntity> findAllByCategoryName(String categoryName);

    List<AdvertisementEntity> findAllByUserId(Long id);

    Optional<AdvertisementEntity> findById(Long advertisementId);

    void deleteAllByUserEmail(String userEmail);

    void deleteByIdAndUserEmail(Long id, String userEmail);

    boolean existsById(Long id);
}
