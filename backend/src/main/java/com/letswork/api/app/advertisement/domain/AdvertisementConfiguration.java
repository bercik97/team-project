package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.category.domain.CategoryFacade;
import com.letswork.api.app.user.domain.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class AdvertisementConfiguration {

    @Bean
    AdvertisementFacade advertisementFacade(AdvertisementRepository repository,
                                            UserFacade userFacade,
                                            CategoryFacade categoryFacade) {
        AdvertisementFactory factory = new AdvertisementFactory();
        AdvertisementValidator validator = new AdvertisementValidator();
        AdvertisementService service = new AdvertisementService(
                repository,
                factory,
                validator,
                userFacade,
                categoryFacade);
        return new AdvertisementFacade(service);
    }

    AdvertisementFacade advertisementFacade(ConcurrentHashMap<String, AdvertisementEntity> db,
                                            UserFacade userFacade,
                                            CategoryFacade categoryFacade) {
        AdvertisementInMemoryRepository repository = new AdvertisementInMemoryRepository(db);
        AdvertisementFactory factory = new AdvertisementFactory();
        AdvertisementValidator validator = new AdvertisementValidator();
        AdvertisementService service = new AdvertisementService(
                repository,
                factory,
                validator,
                userFacade,
                categoryFacade);
        return new AdvertisementFacade(service);
    }
}
