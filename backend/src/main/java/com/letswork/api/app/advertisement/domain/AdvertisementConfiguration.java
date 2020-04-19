package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.user.domain.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class AdvertisementConfiguration {

    @Bean
    AdvertisementFacade advertisementFacade(AdvertisementRepository repository,
                                            UserFacade userFacade) {
        AdvertisementFactory factory = new AdvertisementFactory();
        AdvertisementService service = new AdvertisementService(repository, factory, userFacade);
        return new AdvertisementFacade(service);
    }

    AdvertisementFacade advertisementFacade(ConcurrentHashMap<String, AdvertisementEntity> db,
                                            UserFacade userFacade) {
        AdvertisementInMemoryRepository repository = new AdvertisementInMemoryRepository(db);
        AdvertisementFactory factory = new AdvertisementFactory();
        AdvertisementService service = new AdvertisementService(repository, factory, userFacade);
        return new AdvertisementFacade(service);
    }
}
