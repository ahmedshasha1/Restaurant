package com.resturant.controller;

import com.resturant.dto.CategoryDto;
import com.resturant.model.Category;
import com.resturant.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @PostMapping("/create-category")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    ResponseEntity<Void> save(@RequestBody @Validated CategoryDto categoryDto){
        categoryService.createCategory(categoryDto);
        return ResponseEntity.created(URI.create("/category/create-category")).build();
    }



}
