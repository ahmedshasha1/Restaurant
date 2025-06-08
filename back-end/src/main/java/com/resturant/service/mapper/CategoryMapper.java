package com.resturant.service.mapper;

import com.resturant.dto.CategoryDto;
import com.resturant.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper categoryMapper= Mappers.getMapper(CategoryMapper.class);

     List<CategoryDto> toDtoList(List<Category> categories);
     List<Category> dtoToEntityList(List<CategoryDto> categoryDtos);
     CategoryDto toDto (Category category);

     Category DtoToEntity (CategoryDto categoryDto);
}
