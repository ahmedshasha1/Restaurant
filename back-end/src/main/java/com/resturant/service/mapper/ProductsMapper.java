package com.resturant.service.mapper;

import com.resturant.dto.ProductsDto;
import com.resturant.model.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductsMapper {
    ProductsMapper productMapper= Mappers.getMapper(ProductsMapper.class);

    Products DtotoEntity(ProductsDto productDto);
    ProductsDto toDto(Products product);

    List<Products> DtoToEntityList(List<ProductsDto> productDtos);

    List<ProductsDto> toDtoList(List<Products> products);
}
