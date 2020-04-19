package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.dto.AdvertisementDto;
import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import com.letswork.api.app.category.domain.CategoryEntity;
import com.letswork.api.app.category.domain.CategoryFacade;
import com.letswork.api.app.user.domain.UserEntity;
import com.letswork.api.app.user.domain.UserFacade;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository repository;
    private final AdvertisementFactory factory;
    private final AdvertisementValidator validator;
    private final UserFacade userFacade;
    private final CategoryFacade categoryFacade;

    public void add(CreateAdvertisementDto dto, String userEmail) {
        UserEntity user = userFacade.findByEmail(userEmail);
        CategoryEntity category = categoryFacade.findByCategoryName(dto.getCategoryName());
        validator.validate(dto);
        repository.save(factory.create(dto, user, category));
    }

    public List<AdvertisementDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AdvertisementDto::convert)
                .collect(Collectors.toList());
    }
}
