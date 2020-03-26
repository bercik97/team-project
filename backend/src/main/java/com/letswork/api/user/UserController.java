package com.letswork.api.user;

import com.letswork.api.user.domain.UserFacade;
import com.letswork.api.user.domain.dto.CreateUserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
class UserController {

    private final UserFacade facade;

    @PostMapping
    public void create(@RequestBody CreateUserDto dto) {
        facade.create(dto);
    }
}
