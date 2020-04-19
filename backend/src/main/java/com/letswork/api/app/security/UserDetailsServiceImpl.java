package com.letswork.api.app.security;

import com.letswork.api.app.user.domain.UserFacade;
import com.letswork.api.app.user.domain.dto.SignInDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SignInDto user = userFacade.findByEmailToSignIn(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        if (!user.isEnabled()) {
            user.setPassword(UUID.randomUUID().toString());
            log.warn("User is not verified! Changing password to random UUID!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptySet());
    }
}

