package com.letswork.api.app.advertisement.domain;

import com.letswork.api.app.user.domain.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AdvertisementConfiguration {

    @Bean
    AdvertisementFacade advertisementFacade(AdvertisementRepository repository,
                                            UserFacade userFacade) {
        AdvertisementFactory factory = new AdvertisementFactory();
        AdvertisementService service = new AdvertisementService(repository, factory, userFacade);
        return new AdvertisementFacade(service);
    }
}
