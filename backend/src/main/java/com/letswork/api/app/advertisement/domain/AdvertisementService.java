package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.dto.AdvertisementDto;
import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import com.letswork.api.app.advertisement.domain.dto.UpdateAdvertisementDto;
import com.letswork.api.app.advertisement.domain.exception.InvalidAdvertisementException;
import com.letswork.api.app.category.domain.CategoryEntity;
import com.letswork.api.app.category.domain.CategoryFacade;
import com.letswork.api.app.user.domain.UserEntity;
import com.letswork.api.app.user.domain.UserFacade;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class AdvertisementService {

    private final AdvertisementRepository repository;
    private final AdvertisementFactory factory;
    private final AdvertisementValidator validator;
    private final UserFacade userFacade;
    private final CategoryFacade categoryFacade;

    public void add(CreateAdvertisementDto dto, String userEmail) {
        UserEntity user = userFacade.findByEmail(userEmail);
        CategoryEntity category = categoryFacade.findByCategoryName(dto.getCategoryName());
        validator.validate(dto.getTitle(), dto.getContent());
        repository.save(factory.create(dto, user, category));
    }

    public List<AdvertisementDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(AdvertisementDto::convert)
                .collect(Collectors.toList());
    }

    public List<AdvertisementDto> findAllByCategoryName(String categoryName) {
        return repository
                .findAllByCategoryName(categoryName)
                .stream()
                .map(AdvertisementDto::convert)
                .collect(Collectors.toList());
    }

    public AdvertisementEntity findById(Long advertisementId) {
        return repository
                .findById(advertisementId)
                .orElseThrow(() -> new InvalidAdvertisementException(InvalidAdvertisementException.CAUSE.ADVERTISEMENT_NOT_EXISTS));
    }

    public void deleteAll(String userEmail) {
        repository.deleteAllByUserEmail(userEmail);
    }

    public void deleteById(Long id, String userEmail) {
        validator.validate(id);
        repository.deleteByIdAndUserEmail(id, userEmail);
    }

    public void update(Long id, UpdateAdvertisementDto dto) {
        AdvertisementEntity advertisement = repository
                .findById(id)
                .orElseThrow(() -> new InvalidAdvertisementException(InvalidAdvertisementException.CAUSE.ADVERTISEMENT_NOT_EXISTS));
        String newTitle = dto.getNewTitle();
        String newContent = dto.getNewContent();
        validator.validate(newTitle, newContent);
        advertisement.setTitle(newTitle);
        advertisement.setContent(newContent);
    }
}
