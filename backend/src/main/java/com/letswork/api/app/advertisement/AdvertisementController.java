package com.letswork.api.app.advertisement;

import com.letswork.api.app.advertisement.domain.AdvertisementFacade;
import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/advertisements")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
class AdvertisementController {

    private final AdvertisementFacade facade;

    @PostMapping("add")
    @ApiOperation("Add new advertisement")
    public void create(@RequestBody CreateAdvertisementDto dto, Authentication authentication) {
        facade.add(dto, authentication.getName());
    }

    @GetMapping(path = {
            "find",
            "find/{categoryName}"
    })
    @ApiOperation("Find advertisements and filter them by given category name")
    public ResponseEntity<?> findAllWithOrWithoutCategoryNameFilter(@PathVariable(required = false) String categoryName) {
        return ResponseEntity.ok(facade.findAllWithOrWithoutCategoryNameFilter(categoryName));
    }
}
