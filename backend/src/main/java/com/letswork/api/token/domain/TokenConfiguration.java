package com.letswork.api.token.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
class TokenConfiguration {

    @Bean
    TokenFacade tokenFacade(TokenRepository repository,
                            JavaMailSender mailSender) {
        TokenFactory factory = new TokenFactory();
        TokenService service = new TokenService(repository, factory, mailSender);
        return new TokenFacade(service);
    }
}
