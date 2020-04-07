package com.letswork.api.app.user;

import com.letswork.api.app.user.domain.UserFacade;
import com.letswork.api.app.user.domain.dto.CreateUserDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
class UserController {

    private final UserFacade facade;

    @PostMapping
    @ApiOperation("Add new user")
    public void create(@RequestBody CreateUserDto dto) {
        facade.create(dto);
    }

    @PostMapping("/confirm-account")
    @ApiOperation("Enable user account by passing confirm token")
    public void confirmAccount(@RequestParam("token") String confirmationToken) {
        facade.confirmAccount(confirmationToken);
    }
}
