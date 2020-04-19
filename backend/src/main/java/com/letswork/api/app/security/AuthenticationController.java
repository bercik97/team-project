package com.letswork.api.app.security;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class AuthenticationController {

    @GetMapping
    @ApiOperation("If user is authenticated then show info about him, otherwise return 401 HTTP code")
    public ResponseEntity<?> getAuth(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
        return ResponseEntity.ok(authentication);
    }
}
