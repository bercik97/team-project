package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import com.letswork.api.app.user.domain.UserEntity;
import com.letswork.api.app.user.domain.UserFacade;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AdvertisementService {

    private final AdvertisementRepository repository;
    private final AdvertisementFactory factory;
    private final AdvertisementValidator validator;
    private final UserFacade userFacade;

    public void add(CreateAdvertisementDto dto, String userEmail) {
        UserEntity user = userFacade.findByEmail(userEmail);
        validator.validate(dto);
        repository.save(factory.create(dto, user));
    }
}
