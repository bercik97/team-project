package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.exception.InvalidAdvertisementException;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@AllArgsConstructor
class AdvertisementInMemoryRepository implements AdvertisementRepository {

    private final ConcurrentHashMap<String, AdvertisementEntity> map;

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
    public List<AdvertisementEntity> findAllByUserId(Long id) {
        return map.values()
                .stream()
                .filter(a -> id.equals(a.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdvertisementEntity> findById(Long advertisementId) {
        return Optional.of(map.values()
                .stream()
                .filter(c -> c.getId().equals(advertisementId))
                .findFirst()
                .orElseThrow(() -> new InvalidAdvertisementException(InvalidAdvertisementException.CAUSE.ADVERTISEMENT_NOT_EXISTS)));
    }

    @Override
    public void deleteAllByUserEmail(String userEmail) {
        map.values().removeIf(a -> a.getUser().getEmail().equals(userEmail));
    }

    @Override
    public void deleteByIdAndUserEmail(Long id, String userEmail) {
        map.values().removeIf(a -> a.getId().equals(id) && a.getUser().getEmail().equals(userEmail));
    }

    @Override
    public boolean existsById(Long id) {
        for (AdvertisementEntity advertisement : map.values()) {
            Long iteratedId = advertisement.getId();
            if (iteratedId.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
