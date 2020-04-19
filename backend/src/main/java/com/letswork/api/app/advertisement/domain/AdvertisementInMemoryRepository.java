package com.letswork.api.app.advertisement.domain;

import lombok.AllArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
class AdvertisementInMemoryRepository implements AdvertisementRepository {

    private ConcurrentHashMap<String, AdvertisementEntity> map;

    @Override
    public void save(AdvertisementEntity advertisement) {
        map.put(advertisement.getTitle(), advertisement);
    }
}
