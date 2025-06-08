package com.resturant.service.impl;

import com.resturant.dao.CategoryRepo;
import com.resturant.dto.CategoryDto;
import com.resturant.service.CategoryService;
import com.resturant.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public void createCategory(CategoryDto categoryDto) {
        if(Objects.nonNull(categoryDto.getId())){
            throw new RuntimeException("invalid.id");
        }
        if(categoryRepo.existsByNameIgnoreCase(categoryDto.getName())){
            throw new RuntimeException("invalid.name");
        }

         categoryRepo.save(CategoryMapper.categoryMapper.DtoToEntity(categoryDto));
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        if(Objects.isNull(categoryDto.getId())){
            throw new RuntimeException("invalid.id");
        }
        if(!categoryRepo.existsById(categoryDto.getId())){
            throw new RuntimeException("invalid.id");
        }
        if(categoryRepo.existsByNameIgnoreCase(categoryDto.getName())){
            throw new RuntimeException("invalid.name");
        }

        categoryRepo.save(CategoryMapper.categoryMapper.DtoToEntity(categoryDto));
    }

    @Override
    public void deleteCategory(Long id) {
        if(Objects.nonNull(id)){
            throw new RuntimeException("invalid.id");
        }
        if(!categoryRepo.existsById(id)){
            throw new RuntimeException("invalid.id");
        }
        categoryRepo.deleteById(id);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        if(Objects.nonNull(id)){
            throw new RuntimeException("invalid.id");
        }
        if(!categoryRepo.existsById(id)){
            throw new RuntimeException("invalid.id");
        }
        return CategoryMapper.categoryMapper.toDto(categoryRepo.findCategoriesById(id));
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        return CategoryMapper.categoryMapper.toDtoList(categoryRepo.findAll());
    }
}
