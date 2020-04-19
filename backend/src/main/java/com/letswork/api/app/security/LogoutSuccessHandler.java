package com.letswork.api.app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
class LogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler {

    LogoutSuccessHandler(HttpStatus httpStatusToReturn) {
        super(httpStatusToReturn);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException {
        log.info("Successfully logged out!");
        super.onLogoutSuccess(request, response, authentication);
    }
}

