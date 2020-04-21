package com.letswork.api.app.advertisement;

import com.letswork.api.app.advertisement.domain.AdvertisementFacade;
import com.letswork.api.app.advertisement.domain.dto.CreateAdvertisementDto;
import com.letswork.api.app.advertisement.domain.dto.UpdateAdvertisementDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("find")
    @ApiOperation("Find advertisements")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(facade.findAll());
    }

    @GetMapping("find/{categoryName}")
    @ApiOperation("Find advertisements by given category name")
    public ResponseEntity<?> findAllByCategoryName(@PathVariable String categoryName) {
        return ResponseEntity.ok(facade.findAllByCategoryName(categoryName));
    }

    @PutMapping("update/{id}")
    @ApiOperation("Update title and content of advertisement")
    public void update(@PathVariable Long id, @RequestBody UpdateAdvertisementDto dto) {
        facade.update(id, dto);
    }

    @DeleteMapping("delete")
    @ApiOperation("Delete all advertisements")
    public void deleteAll(Authentication authentication) {
        facade.deleteAll(authentication.getName());
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("Delete advertisement by id")
    public void deleteById(@PathVariable Long id, Authentication authentication) {
        facade.deleteById(id, authentication.getName());
    }
}
