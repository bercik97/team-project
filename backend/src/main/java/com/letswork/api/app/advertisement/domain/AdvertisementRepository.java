package com.letswork.api.app.advertisement.domain;

import org.springframework.data.repository.Repository;

interface AdvertisementRepository extends Repository<AdvertisementEntity, Long> {

    void save(AdvertisementEntity advertisement);
}
