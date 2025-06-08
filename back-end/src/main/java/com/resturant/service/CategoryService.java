package com.resturant.service;

import com.resturant.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory (CategoryDto categoryDto);
    void updateCategory(CategoryDto categoryDto );
    void deleteCategory (Long id);
    CategoryDto getCategoryById (Long id);

    List<CategoryDto> getAllCategory ();
}
