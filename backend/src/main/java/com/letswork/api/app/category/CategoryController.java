package com.letswork.api.app.category;

import com.letswork.api.app.category.domain.CategoryFacade;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/categories")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
class CategoryController {

    private final CategoryFacade facade;

    @GetMapping
    @ApiOperation("Find categories")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(facade.findAll());
    }
}
