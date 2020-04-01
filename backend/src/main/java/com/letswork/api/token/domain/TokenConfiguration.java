package com.letswork.api.token.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TokenConfiguration {

    @Bean
    TokenFacade tokenFacade(TokenRepository repository) {
        TokenService service = new TokenService(repository);
        return new TokenFacade(service);
    }
}
