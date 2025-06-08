package com.resturant.service;

import com.resturant.controller.vm.ProductResponseVM;
import com.resturant.dto.ProductsDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductsDto productsDto);
    void updateProduct(ProductsDto productsDto);
    void deleteProduct(Long id);

    ProductResponseVM getProductsByCategoryID(Long id, Integer pageNo, Integer pageSize);

    ProductResponseVM getAllProductByNameOrDescription(String name,Integer pageNo,Integer pageSize) throws RuntimeException;
    ProductResponseVM getAllProductByLetter(String letter,Integer pageNo,Integer pageSize) throws RuntimeException;
    ProductResponseVM getAllProducts(Integer pageNo,Integer pageSize);

    List<ProductsDto> findProductsById(List<Long> productIds);
}
