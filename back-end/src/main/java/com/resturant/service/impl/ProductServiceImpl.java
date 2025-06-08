package com.resturant.service.impl;

import com.resturant.controller.vm.ProductResponseVM;
import com.resturant.dao.CategoryRepo;
import com.resturant.dao.ProductsRepo;
import com.resturant.dto.ProductsDto;
import com.resturant.model.Products;
import com.resturant.service.ProductService;
import com.resturant.service.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public void createProduct(ProductsDto productsDto) {
        if(Objects.nonNull(productsDto.getId())){
            throw new RuntimeException("invalid.id");
        }
        if(productsRepo.existsByNameIgnoreCase(productsDto.getName())){
            throw new RuntimeException("invalid.name");
        }
        if(Objects.isNull(productsDto.getCategory())){
            throw new RuntimeException("invalid.id");
        }

         productsRepo.save(ProductsMapper.productMapper.DtotoEntity(productsDto));
    }

    @Override
    public void updateProduct(ProductsDto productsDto) {
        if (Objects.isNull(productsDto.getId())) {
            throw new RuntimeException("invalid.id");
        }
        if (!productsRepo.existsById(productsDto.getId())) {
            throw new RuntimeException("invalid.id");
        }
        if (productsRepo.existsByNameIgnoreCase(productsDto.getName())) {
            throw new RuntimeException("invalid.name");
        }
        productsRepo.save(ProductsMapper.productMapper.DtotoEntity(productsDto));
    }

    @Override
    public void deleteProduct(Long id) {
        if (Objects.isNull(id) ){
            throw new RuntimeException("invalid.id");
        }
        if (!productsRepo.existsById(id)) {
            throw new RuntimeException("invalid.id");
        }
        Products products = productsRepo.findProductsById(id);
        products.setOrders(null);
        products.setCategory(null);

        productsRepo.deleteById(id);

    }

    @Override
    public ProductResponseVM getProductsByCategoryID(Long id, Integer pageNo, Integer pageSize) {
        if (Objects.isNull(id) ){
            throw new RuntimeException("invalid.id");
        }
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Products> productsPage =  productsRepo.findByCategory_Id(id,pageable);
        return new ProductResponseVM(
            ProductsMapper.productMapper.toDtoList(productsPage.getContent()),
             productsPage.getTotalElements()
        );
    }

    @Override
    public ProductResponseVM getAllProductByNameOrDescription(String key, Integer pageNo, Integer pageSize) throws RuntimeException {
        if(Objects.isNull(key)){
            throw new RuntimeException("invalid.key");
        }
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Products> productsPage =  productsRepo.findAllProductByNameOrDescriptionContaining(key,pageable);
        if(productsPage.isEmpty()){
            throw new RuntimeException("invalid.key");
        }

        return new ProductResponseVM(
                ProductsMapper.productMapper.toDtoList(productsPage.getContent()),
                productsPage.getTotalElements()
        );
    }

    @Override
    public ProductResponseVM getAllProductByLetter(String letter, Integer pageNo, Integer pageSize) throws RuntimeException {
        if(Objects.isNull(letter)){
            throw new RuntimeException("invalid.letter");
        }
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Products> productsPage = productsRepo.findByNameAndDescriptionContaining(letter,pageable);
        if(productsPage.isEmpty()){
            throw new RuntimeException("invalid.letter");
        }

        return new ProductResponseVM(
                ProductsMapper.productMapper.toDtoList(productsPage.getContent()),
                productsPage.getTotalElements()
    );
    }
    @Override
    public ProductResponseVM getAllProducts(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Products> productsPage = productsRepo.findAll(pageable);
        return new ProductResponseVM(
                ProductsMapper.productMapper.toDtoList(productsPage.getContent()),
                productsPage.getTotalElements()
        );
    }

    @Override
    public List<ProductsDto> findProductsById(List<Long> productIds) {
        if (Objects.isNull(productIds) ){
            throw new RuntimeException("invalid.id");
        }
        return ProductsMapper.productMapper.toDtoList(productsRepo.findAllById(productIds));
    }


}
