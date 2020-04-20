package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.exception.InvalidAdvertisementException;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@AllArgsConstructor
class AdvertisementInMemoryRepository implements AdvertisementRepository {

    private ConcurrentHashMap<String, AdvertisementEntity> map;

    @Override
    public void save(AdvertisementEntity advertisement) {
        map.put(advertisement.getTitle(), advertisement);
    }

    @Override
    public List<AdvertisementEntity> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public List<AdvertisementEntity> findAllByCategoryName(String categoryName) {
        return map.values()
                .stream()
                .filter(a -> categoryName.equals(a.getCategory().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public AdvertisementEntity findById(Long advertisementId) {
        return map.values()
                .stream()
                .filter(c -> c.getId().equals(advertisementId))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidAdvertisementException(InvalidAdvertisementException.CAUSE.ADVERTISEMENT_NOT_EXISTS));
    }
}
